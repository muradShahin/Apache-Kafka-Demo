package com.mtech.kafka_v1;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class producerDemoWithOutCallBack {

	private static final Logger log = LoggerFactory.getLogger(producerDemoWithOutCallBack.class);
	private static final Properties properties = new Properties();

	public static void main(String[] args) {

		log.info("kafka");

		setProducerProperties();
		// create Producer
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

		// create producer record
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("java-topic",
				"first push from java");

		// send data using the producer
		kafkaProducer.send(producerRecord);

		// flush the producer , this tells the producer to send all data and block until
		// done --synchronus
		kafkaProducer.flush();

		// close the producer
		kafkaProducer.close();

		// to test and consume from the topic you can use Kafka CLI by running the below
		// command
		// kafka-console-consumer --bootstrap-server localhost:19092 --topic java-topic
		// --from-beginning
	}

	private static void setProducerProperties() {

		properties.setProperty("bootstrap.servers", "127.0.0.1:19092");

		// set producer properties
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());

	}

}
