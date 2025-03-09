package com.example.javaspring.controller;

import com.example.javaspring.services.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducer.sendMessage("my-topic", message);
        return "Message sent!";
    }
}
