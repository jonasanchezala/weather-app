package org.interview.service;

import org.interview.model.SensorInput;
import org.interview.model.SensorMeasurement;
import org.interview.parser.MessageParser;
import org.interview.producer.MessageProducer;
import org.interview.protobuf.SensorMessage.SensorType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final MessageParser<SensorInput, SensorMeasurement> messageParser;
    private final MessageProducer messageProducer;

    public SensorService(@Qualifier("message") MessageParser<SensorInput, SensorMeasurement> messageParser, MessageProducer messageProducer) {
        this.messageParser = messageParser;
        this.messageProducer = messageProducer;
    }

    public void processMessage(String message, SensorType sensorType) {
        messageParser
                .parse(SensorInput.builder().msg(message).sensorType(sensorType).build())
                .flatMap(SensorMeasurement::toProto)
                .ifPresent(sensorData -> messageProducer.send(sensorData.toByteArray()));
    }
}
