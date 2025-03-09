package com.example.javaspring.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_kafka")
public class MessageKafka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Column(columnDefinition = "TEXT",name = "message")
    private String message;

    private LocalDateTime receivedAt = LocalDateTime.now();

    private String status; // Idle, Consume

    // Constructors
    public MessageKafka() {}

    public MessageKafka(String topic, String content) {
        this.topic = topic;
        this.message = content;
        this.status = "Idle";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getContent() { return message; }
    public void setContent(String content) { this.message = content; }

    public LocalDateTime getReceivedAt() { return receivedAt; }
    public void setReceivedAt(LocalDateTime receivedAt) { this.receivedAt = receivedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}