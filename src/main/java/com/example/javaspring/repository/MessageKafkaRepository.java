package com.example.javaspring.repository;

import com.example.javaspring.model.MessageKafka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageKafkaRepository extends JpaRepository<MessageKafka, Long> {
    List<MessageKafka> findByStatus(String status);
}