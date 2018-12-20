package com.ote.test.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MessageBinding.class)
@Slf4j
public class KafkaStreamConsumerConfig {

    @StreamListener
    public void streamtest(@Input("inputStreamTest") KStream<String, String> input) {
        KStream<String, String>[] branch = input.branch((k, v) -> "0".equals(k), (k, v) -> "1".equals(k), (k, v) -> true);
        branch[0].peek((k, v) -> log.warn(" ------> Reading(0)\t: " + v));
        branch[1].peek((k, v) -> log.warn(" ------> Reading(1)\t: " + v));
        branch[2].peek((k, v) -> log.warn(" ------> Other(" + k + ")\t: " + v));
    }
}