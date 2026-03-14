package org.interview.handler;

import org.interview.protobuf.SensorMessage.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.interview.handler.HumiditySensorAlarm.ALARM_THRESHOLD;
import static org.junit.jupiter.api.Assertions.*;

class HumiditySensorAlarmTest {

    private HumiditySensorAlarm alarm;

    @BeforeEach
    void setUp() {
        alarm = new HumiditySensorAlarm();
    }

    @Test
    void getType_returnsHumidity() {
        assertEquals(SensorType.HUMIDITY, alarm.getType());
    }

    @Test
    void threshold_returns50() {
        assertEquals(ALARM_THRESHOLD, alarm.threshold());
    }

    @Test
    void shouldTrigger_valueAboveThreshold_returnsTrue() {
        assertTrue(alarm.shouldTrigger(51.0));
    }

    @Test
    void shouldTrigger_valueAtThreshold_returnsFalse() {
        assertFalse(alarm.shouldTrigger(50.0));
    }

    @Test
    void shouldTrigger_valueBelowThreshold_returnsFalse() {
        assertFalse(alarm.shouldTrigger(49.9));
    }

    @Test
    void getMessage_returnsCorrectFormat() {
        String message = alarm.getMessage("sensor-01", 75.0);

        assertEquals(
                "Sensor Alarm was triggered with type=HUMIDITY, id= sensor-01 value=75.0",
                message
        );
    }
}