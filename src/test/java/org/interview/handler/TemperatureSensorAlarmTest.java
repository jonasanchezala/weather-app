package org.interview.handler;

import org.interview.protobuf.SensorMessage.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.interview.handler.TemperatureSensorAlarm.ALARM_THRESHOLD;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureSensorAlarmTest {

    private TemperatureSensorAlarm alarm;

    @BeforeEach
    void setUp() {
        alarm = new TemperatureSensorAlarm();
    }

    @Test
    void getType_returnsTemperature() {
        assertEquals(SensorType.TEMPERATURE, alarm.getType());
    }

    @Test
    void threshold_returns50() {
        assertEquals(ALARM_THRESHOLD, alarm.threshold());
    }

    @Test
    void shouldTrigger_valueAboveThreshold_returnsTrue() {
        assertTrue(alarm.shouldTrigger(36.0));
    }

    @Test
    void shouldTrigger_valueAtThreshold_returnsFalse() {
        assertFalse(alarm.shouldTrigger(35.0));
    }

    @Test
    void shouldTrigger_valueBelowThreshold_returnsFalse() {
        assertFalse(alarm.shouldTrigger(34.9));
    }

    @Test
    void getMessage_returnsCorrectFormat() {
        String message = alarm.getMessage("sensor-01", 75.0);

        assertEquals(
                "Sensor Alarm was triggered with type=TEMPERATURE, id= sensor-01 value=75.0",
                message
        );
    }
}