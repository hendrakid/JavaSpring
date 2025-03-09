package com.example.javaspring.component;

import com.example.javaspring.model.EmployeeRecord;
import com.example.javaspring.model.MessageKafka;
import com.example.javaspring.repository.EmployeeRecordRepository;
import com.example.javaspring.repository.MessageKafkaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class MyScheduler {

    @Autowired
    private MessageKafkaRepository messageKafkaRepository;

    @Autowired
    private EmployeeRecordRepository employeeRecordRepository;

    @Autowired
    private ObjectMapper objectMapper; // To parse JSON

    @Scheduled(fixedRate = 1000)  // Runs every 1 seconds
    public void testTask() {

        List<MessageKafka> idleMessages = messageKafkaRepository.findByStatus("Idle");

        for (MessageKafka message : idleMessages) {
            try {
                // Parse JSON to extract employeeId
                JsonNode jsonNode = objectMapper.readTree(message.getContent());
                Long employeeId = jsonNode.get("id").asLong();  // Extract "id" field

                // Save employee record
                EmployeeRecord employeeRecord = new EmployeeRecord(employeeId, LocalDateTime.now());
                employeeRecordRepository.save(employeeRecord);

                // Update message status to "Consume"
                message.setStatus("Consume");
                messageKafkaRepository.save(message);

                System.out.println("✅ Processed message for Employee ID: " + employeeId);
            } catch (Exception e) {
                System.err.println("❌ Error processing message: " + e.getMessage());
            }
        }
    }
}