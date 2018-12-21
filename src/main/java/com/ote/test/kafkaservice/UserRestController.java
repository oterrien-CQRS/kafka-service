package com.ote.test.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class UserRestController {

    private final Source source;

    @Autowired
    public UserRestController(Source source) {
        this.source = source;
    }

    @GetMapping("/user")
    public void test() {
        Message<User> message = MessageBuilder.withPayload(new User("steve", "jobs")).build();
        source.output().send(message);
        System.out.println("Sent --> " + message.getPayload().toString());
    }
}
