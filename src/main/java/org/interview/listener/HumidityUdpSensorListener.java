package org.interview.listener;

import lombok.extern.slf4j.Slf4j;
import org.interview.protobuf.SensorMessage.SensorType;
import org.interview.service.SensorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HumidityUdpSensorListener extends AbstractUdpSensorListener {

    @Value("${udp.humidity-sensor-port}")
    private int port;

    public HumidityUdpSensorListener(SensorService sensorService) {
        super(sensorService);
    }

    @Override protected int        getPort()       { return port; }
    @Override protected SensorType getSensorType() { return SensorType.HUMIDITY; }
}