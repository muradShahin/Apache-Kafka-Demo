package com.mtech.kafka_v1;

import java.util.Properties;

import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoWithCallBack {

	private final static Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallBack.class);
	private final static Properties properties = new Properties();

	public static void main(String args[]) {

		logger.info("Producer with callback demo");
		setProducerProperties();
		String messages[] = new String[5];
		messages[0]="customer details";
		messages[1]="account details";
		messages[2]="statement push";
		messages[3]="STP file";
		messages[4]="FT swift txn";
		
		sendBatches(messages);

		

	}

	private static void setProducerProperties() {

		properties.setProperty("bootstrap.servers", "127.0.0.1:19092");

		// set producer properties
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());

	}

	private static void sendBatches(String data[]) {

		// create the producer
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

		for (String msg : data) {

			// create the prodcuer record object

			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("java-topic", msg);

			// send the message to topic
			kafkaProducer.send(producerRecord, new Callback() {

				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {

					if (exception == null) {
						logger.info("Message Sent Successfullt\n" + "Topic: " + metadata.topic() + "\n" + "Offset: "
								+ metadata.offset() + "\n" + "Partition: " + metadata.partition() + "\n" + "Timestamp: "
								+ metadata.timestamp());
					} else {
						logger.error("message was not sent due to the error : " + exception);
					}

				}
			});

		}
		//closing the producer which will call the flush function as well
		kafkaProducer.close();

	}

}
