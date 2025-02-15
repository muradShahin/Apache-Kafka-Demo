package com.mtech.kafka_v1.consumersDemo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo1 {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerDemo1.class);
	private static final Properties properties = new Properties();
	private static final ArrayList<String> topics = new ArrayList<>();

	private static KafkaConsumer<String, String> consumer;

	public static void main(String args[]) {

		topics.add("java-topic");
		setConsumerProperties();
		createKafkaConsumer();
		consumeTopic();

	}

	private static void setConsumerProperties() {
		properties.setProperty("bootstrap.servers", "127.0.0.1:19092");
		properties.setProperty("key.deserializer", StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", StringDeserializer.class.getName());
		properties.setProperty("group.id", "console-consumer-11649");
		properties.setProperty("auto.offset.reset", "earliest"); // earliest == --from-begining in the CI

	}

	private static void createKafkaConsumer() {
		consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(topics);

	}

	private static void consumeTopic() {

		// we need to create consumer records object first
		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				
				logger.info("value: "+consumerRecord.value());
				
			}
		}

	}
}
