package com.mtech.kafka_v1;

import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoWithIds {

	private static final Logger logger = LoggerFactory.getLogger(ProducerDemoWithIds.class);
	private static final Properties properties = new Properties();

	public static void main(String args[]) {
		
		
		setProducerProperties();
		
		String messages[] = new String[9];
		messages[0]="customer details";
		messages[1]="account details";
		messages[2]="statement push";
		messages[3]="STP file";
		messages[4]="FT swift txn";
		messages[5]="FT swift txn2";
		messages[6]="FT swift txn4";
		messages[7]="FT swift txn6";
		messages[8]="FT swift txn7";

		/**
		 * now we want to send multiple messages using the producer with id for each
		 * message
		 */
		sendMessages(messages);

	}

	private static void setProducerProperties() {
		properties.setProperty("bootstrap.servers", "127.0.0.1:19092");
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());

	}

	private static void sendMessages(String data[]) {

		// creating kafka producer with the above properties
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		String topic = "java-topic";

		for (int i = 0; i < data.length; i++) {

			for (int j = 0; j < 10; j++) {

				String key = "id_" + i;
				String value = "message" + i;

				// i will do another loop inside to demonstrate how messages with the same ID
				// will be sent
				// to the same partition

				// creating the producer records
				ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key, value);
				producer.send(producerRecord, new Callback() {

					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						// TODO Auto-generated method stub

						if (exception == null) {
							logger.info("Message Sent Successfullt\n" + "Topic: " + metadata.topic() + "\n" + "Offset: "
									+ metadata.offset() + "\n" + "Partition: " + metadata.partition() + "\n"
									+ "Timestamp: " + metadata.timestamp());
						} else {
							logger.error("message was not sent due to the error : " + exception);
						}

					}
				});

			}

		}
		
		producer.close();

	}

}
