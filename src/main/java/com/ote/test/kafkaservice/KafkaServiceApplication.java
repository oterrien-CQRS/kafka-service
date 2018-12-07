package com.ote.test.kafkaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaServiceApplication {


	/**
	 * bin/kafka-topics.bat --create  --zookeeper localhost:2181  --replication-factor 1 --partitions 1  --topic olivier
	 *
	 */

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);
	}
}
