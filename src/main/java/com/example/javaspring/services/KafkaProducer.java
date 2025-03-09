package com.example.javaspring.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper; // To convert object to JSON

    public void sendMessage(String topic, Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, jsonMessage);
            // save ke db

            System.out.println("Sent message: " + jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.err.println("Error converting message to JSON: " + e.getMessage());
        }
    }
}
