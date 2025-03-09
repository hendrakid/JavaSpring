package com.example.javaspring.services;

import com.example.javaspring.model.Employee;
import com.example.javaspring.model.MessageKafka;
import com.example.javaspring.repository.MessageKafkaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageKafkaRepository messageKafkaRepository;

    @KafkaListener(topics = "employee", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            // Convert JSON string back to Employee object
            Employee employee = objectMapper.readValue(record.value(), Employee.class);
            System.out.println("Received Employee: " + employee);


            String topic = record.topic();
            String messageContent = record.value();

            // Save to database
            MessageKafka message = new MessageKafka(topic, messageContent);
            messageKafkaRepository.save(message);

            System.out.println("Saved message: " + messageContent);


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error parsing Kafka message: " + e.getMessage());
        }
    }
}
