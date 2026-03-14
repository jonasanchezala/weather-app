package org.interview.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.interview.protobuf.SensorMessage.SensorType;

@Builder
@Slf4j
public record SensorInput(String msg, SensorType sensorType) {
}
