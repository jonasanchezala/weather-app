package org.interview.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.interview.protobuf.SensorMessage.SensorType;

@Component
@Slf4j
public class TemperatureSensorAlarm implements SensorAlarm {

    public static final double ALARM_THRESHOLD = 35.0;

    @Override
    public SensorType getType() {
        return SensorType.TEMPERATURE;
    }

    @Override
    public double threshold() {
        return ALARM_THRESHOLD;
    }


}