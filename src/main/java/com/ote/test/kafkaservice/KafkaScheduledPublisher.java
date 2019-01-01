package com.ote.test.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
@Slf4j
public class KafkaScheduledPublisher {

    private final Source source;

    @Autowired
    public KafkaScheduledPublisher(Source source) {
        this.source = source;
        log.warn(this.getClass().getName() + " has been started");
    }

    @Scheduled(fixedDelay = 1000L)
    public void publish() {
        Message<User> message = MessageBuilder.withPayload(new User("steve", "jobs")).build();
        source.output().send(message);
        log.warn("Sent --> " + message.getPayload().toString());
    }
}
