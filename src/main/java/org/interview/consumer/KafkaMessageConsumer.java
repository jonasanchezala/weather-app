package org.interview.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.interview.service.MonitorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageConsumer implements MessageConsumer {

    private final MonitorService monitorService;

    @KafkaListener(topics = "${kafka.topic.sensor}", groupId = "${kafka.group-id}")
    public void consume(byte[] data) {
        log.info("Message consumed from kafka");
        monitorService.evaluateSensorData(data);
    }
}