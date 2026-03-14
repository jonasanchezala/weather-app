package org.interview.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.interview.protobuf.SensorMessage;
import org.interview.protobuf.SensorMessage.SensorType;

import java.util.Optional;

@Builder
@Slf4j
public record SensorMeasurement(String id, Double value, SensorType sensorType) {

    public Optional<SensorMessage.SensorData> toProto(){
        try{
            return  Optional.of(SensorMessage.SensorData.newBuilder()
                    .setSensorId(id())
                    .setType(SensorType.valueOf(sensorType().name()))
                    .setValue(value())
                    .build());
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

}
