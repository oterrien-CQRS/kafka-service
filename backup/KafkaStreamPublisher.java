package com.ote.test.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
@EnableBinding(MessageBinding.class)
@Slf4j
public class KafkaStreamPublisher {

    /*@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
*/
    private final MessageChannel messageChannel;

    public KafkaStreamPublisher(@Autowired MessageBinding messageBinding) {
        this.messageChannel = messageBinding.output();
    }

    @Scheduled(fixedDelay = 1000L)
    public void populate() {
        int key = new Random().nextInt(10);
        String value = UUID.randomUUID().toString();
        log.info("publishing : " + key + ":" + value);
        Message<String> message = MessageBuilder.withPayload(value).setHeader(KafkaHeaders.MESSAGE_KEY, Integer.toString(key).getBytes()).build();
        this.messageChannel.send(message);

        //kafkaTemplate.send("streamtest", Integer.toString(key), value);
    }


}
