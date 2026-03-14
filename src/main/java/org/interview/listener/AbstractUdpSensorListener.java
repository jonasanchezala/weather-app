package org.interview.listener;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.interview.protobuf.SensorMessage.SensorType;
import org.interview.service.SensorService;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractUdpSensorListener implements SensorListener {

    private final SensorService sensorService;
    private Connection connection;

    protected abstract int getPort();
    protected abstract SensorType getSensorType();

    @PostConstruct
    @Override
    public void start() {
        connection = UdpServer.create()
                .host("0.0.0.0")
                .port(getPort())
                .handle((in, out) ->
                        in.receive()
                                .asString()
                                .doOnNext(message -> sensorService.processMessage(message, getSensorType()))
                                .doOnError(e -> log.error(
                                        "Failed to process message type={} port={}",
                                        getSensorType(), getPort(), e))
                                .then()
                )
                .bindNow();

        log.info("UDP listener started type={} port={}", getSensorType(), getPort());
    }

    @PreDestroy
    @Override
    public void stop() {
        if (connection != null) {
            connection.disposeNow();
            log.info("UDP listener stopped type={} port={}", getSensorType(), getPort());
        }
    }
}