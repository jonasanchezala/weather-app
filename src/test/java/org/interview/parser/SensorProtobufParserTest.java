package org.interview.parser;

import org.interview.protobuf.SensorMessage.SensorData;
import org.interview.protobuf.SensorMessage.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SensorProtobufParserTest {

    private SensorProtobufParser parser;
    private byte[] validBytes;

    @BeforeEach
    void setUp() {
        parser = new SensorProtobufParser();

        SensorData validSensorData = SensorData.newBuilder()
                .setSensorId("sensor-01")
                .setValue(36.5)
                .setType(SensorType.TEMPERATURE)
                .build();

        validBytes = validSensorData.toByteArray();
    }

    @Test
    void parse_validBytes_returnsSensorData() {
        Optional<SensorData> result = parser.parse(validBytes);

        assertTrue(result.isPresent());
        assertEquals("sensor-01",        result.get().getSensorId());
        assertEquals(36.5,               result.get().getValue(), 0.001);
        assertEquals(SensorType.TEMPERATURE, result.get().getType());
    }

    @Test
    void parse_invalidBytes_returnsEmpty() {
        byte[] bytes = "invalid".getBytes();

        Optional<SensorData> result = parser.parse(bytes);

        assertTrue(result.isEmpty());
    }
}

