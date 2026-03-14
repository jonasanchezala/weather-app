package org.interview.parser;

import org.interview.model.SensorInput;
import org.interview.model.SensorMeasurement;
import org.interview.protobuf.SensorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorMessageParserTest {

    private SensorMessageParser parser;

    @BeforeEach
    void setUp() {
        parser = new SensorMessageParser();
    }

    @Test
    void parse_validMessage_returnsSensorMeasurement() {
        SensorInput input = new SensorInput("id=sensor-01;value=36.5", SensorMessage.SensorType.TEMPERATURE);

        Optional<SensorMeasurement> result = parser.parse(input);

        assertTrue(result.isPresent());
        assertEquals("sensor-01",      result.get().id());
        assertEquals(36.5,result.get().value());
        assertEquals(SensorMessage.SensorType.TEMPERATURE, result.get().sensorType());
    }

    @Test
    void parse_invalidMessage_returnsEmpty() {
        SensorInput input = new SensorInput("sensor-01 36.5", SensorMessage.SensorType.TEMPERATURE);

        Optional<SensorMeasurement> result = parser.parse(input);

        assertTrue(result.isEmpty());
    }
}