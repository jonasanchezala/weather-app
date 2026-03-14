package org.interview.producer;

public interface MessageProducer {
    void send(byte[] message);
}
