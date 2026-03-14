package org.interview.parser;

import lombok.extern.slf4j.Slf4j;
import org.interview.model.SensorInput;
import org.interview.model.SensorMeasurement;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("message")
@Slf4j
public class SensorMessageParser implements MessageParser<SensorInput, SensorMeasurement> {

    public static final String FIELD_SEPARATOR = ";";
    public static final String VALUE_SEPARATOR = "=";

    public Optional<SensorMeasurement> parse(SensorInput sensorInput) {
        try{
            String[] parts = sensorInput.msg().split(FIELD_SEPARATOR);
            String id = parts[0].split(VALUE_SEPARATOR)[1].trim();
            double val = Double.parseDouble(parts[1].split(VALUE_SEPARATOR)[1].trim());
            return Optional.of(SensorMeasurement.builder()
                    .id(id)
                    .value(val)
                    .sensorType(sensorInput.sensorType())
                    .build());
        } catch (Exception e){
            log.error(e.getMessage());
        }

        return Optional.empty();
    }
}
