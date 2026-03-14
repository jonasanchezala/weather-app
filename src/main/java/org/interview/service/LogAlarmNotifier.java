package org.interview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogAlarmNotifier implements AlarmNotifier {
    public void notifyMessage(String message) {
        log.warn(message);
    }
}
