package org.interview.service;

import org.interview.handler.SensorAlarm;
import org.interview.handler.SensorAlarmFactory;
import org.interview.parser.MessageParser;
import org.interview.protobuf.SensorMessage.SensorData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MonitorService {

    private final MessageParser<byte[], SensorData> messageParser;
    private final SensorAlarmFactory sensorAlarmFactory;
    private final AlarmNotifier alarmNotifier;

    public MonitorService(@Qualifier("proto") MessageParser<byte[], SensorData> messageParser,
                          SensorAlarmFactory sensorAlarmFactory,
                          AlarmNotifier alarmNotifier) {
        this.messageParser = messageParser;
        this.sensorAlarmFactory = sensorAlarmFactory;
        this.alarmNotifier = alarmNotifier;
    }

    public void evaluateSensorData(byte[] sensorDataBytes) {
        messageParser.parse(sensorDataBytes)
                .ifPresent(this::evaluate);
    }

    private void evaluate(SensorData sensorData) {
        SensorAlarm sensorAlarm = sensorAlarmFactory.create(sensorData.getType());
        double value = sensorData.getValue();
        if(sensorAlarm.shouldTrigger(value)){
            alarmNotifier.notifyMessage(sensorAlarm.getMessage(sensorData.getSensorId(), sensorData.getValue()));
        }
    }
}
