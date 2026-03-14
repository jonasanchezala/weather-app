package org.interview.handler;


import org.interview.protobuf.SensorMessage.SensorType;

public interface SensorAlarm {
    SensorType getType();
    double threshold();

    default boolean shouldTrigger(double value) {
        return value > threshold();
    };

    default String getMessage(String id, double value) {
        return "Sensor Alarm was triggered with type=" + getType().name() + ", id= " + id + " value=" + value;
    }
}