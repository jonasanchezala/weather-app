package org.interview.handler;

import org.interview.protobuf.SensorMessage.SensorType;
import org.springframework.stereotype.Component;

@Component
public class HumiditySensorAlarm implements SensorAlarm {

    public static final double ALARM_THRESHOLD = 50.0;

    @Override
    public SensorType getType() {
        return SensorType.HUMIDITY;
    }

    @Override
    public double threshold() {
        return ALARM_THRESHOLD;
    }
}