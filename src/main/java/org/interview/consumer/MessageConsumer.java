package org.interview.consumer;

public interface MessageConsumer {
    void consume(byte[] data);
}
