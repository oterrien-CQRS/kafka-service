package com.ote.test.kafkaservice;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageBinding {

    @Input("inputStreamTest")
    KStream<String, String> input();

    @Output("outputStreamTest")
    MessageChannel output();
}