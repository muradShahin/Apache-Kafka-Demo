# **Kafka Producer & Consumer Demo in Java**  

A simple Java application demonstrating how to produce and consume messages in Apache Kafka. This project includes:  

✔️ A basic producer that sends messages  
✔️ A producer with message IDs and a callback function  
✔️ A consumer that listens for messages  

## **📌 Prerequisites**  
Ensure you have the following installed:  
- **Java 8+**  
- **Apache Kafka** (Running on `localhost:19092`)  

## **🚀 Getting Started**  

### **1️⃣ Start Kafka Broker**  
Make sure your Kafka broker is running. If using Docker:  
```sh
docker-compose up -d
```
🔧 Kafka CLI Testing
To check if messages are being received, you can use Kafka CLI:

```sh
kafka-console-consumer --bootstrap-server localhost:19092 --topic java-topic --from-beginning
```
🌟 Features
✔ Basic Producer - Sends a simple message
✔ Producer with IDs - Sends messages with a unique key for partitioning
✔ Consumer - Listens and processes messages from a Kafka topic

📌 Future Enhancements
🔹 Add error handling and retry mechanism
🔹 Implement batch processing in consumers
🔹 Add Spring Boot support for Kafka
