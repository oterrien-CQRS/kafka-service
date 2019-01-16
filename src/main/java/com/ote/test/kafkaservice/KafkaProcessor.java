package com.ote.test.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
@Slf4j
public class KafkaProcessor {

    private final Sink sink;

    @Autowired
    public KafkaProcessor(Sink sink) {
        this.sink = sink;
    }

    @StreamListener(value = Sink.INPUT)
    public void consume(Message<User> message) {
        log.warn("Received <-- " + message.getPayload().toString());
    }
}
