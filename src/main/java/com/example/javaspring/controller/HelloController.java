package com.example.javaspring.controller;

import com.example.javaspring.dto.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping(value = "/hello-world")
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}