package com.ote.test.kafkaservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class KafkaRestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/test")
    public void test(@RequestBody String message) {
        kafkaTemplate.send("olivier", message + "_" + System.currentTimeMillis());
    }
}
