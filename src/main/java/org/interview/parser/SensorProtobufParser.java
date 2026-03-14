package org.interview.parser;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.interview.protobuf.SensorMessage.SensorData;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("proto")
@Slf4j
public class SensorProtobufParser implements MessageParser<byte[], SensorData>{

    public Optional<SensorData> parse(byte[] data) {
        try {
            return Optional.of(SensorData.parseFrom(data));
        } catch (InvalidProtocolBufferException e) {
            log.error("Unable to parse message", e);
        }

        return Optional.empty();
    }
}
