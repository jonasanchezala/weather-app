package org.interview.handler;

import lombok.RequiredArgsConstructor;
import org.interview.protobuf.SensorMessage.SensorType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorAlarmFactory {

    private final TemperatureSensorAlarm temperatureSensorAlarm;
    private final HumiditySensorAlarm humiditySensorAlarm;

    public SensorAlarm create(SensorType type) {
        return switch (type) {
            case TEMPERATURE -> temperatureSensorAlarm;
            case HUMIDITY    -> humiditySensorAlarm;
            default -> throw new UnsupportedOperationException("Alarm not implemented with type: " + type.name());
        };
    }
}
