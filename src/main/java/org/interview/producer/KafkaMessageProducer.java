package org.interview.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageProducer implements MessageProducer {

    @Value("${kafka.topic.sensor}")
    private String topic;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void send(byte[] message) {
        kafkaTemplate.send(topic, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Message sent to topic {}", topic);
                    } else {
                        log.info("Unable to sent message to topic {}", topic);
                    }
                });
    }
}