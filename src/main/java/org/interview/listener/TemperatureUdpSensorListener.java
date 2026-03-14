package org.interview.listener;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.interview.protobuf.SensorMessage.SensorType ;
import org.interview.service.SensorService;
import org.springframework.beans.factory.annotation.Value;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TemperatureUdpSensorListener extends AbstractUdpSensorListener {

    @Value("${udp.temperature-sensor-port}")
    private int port;

    public TemperatureUdpSensorListener(SensorService sensorService) {
        super(sensorService);
    }

    @Override protected int        getPort()       { return port; }
    @Override protected SensorType getSensorType() { return SensorType.TEMPERATURE; }
}