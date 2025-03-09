/*
package com.example.javaspring.controller;

import com.example.javaspring.dto.Greeting;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void index() throws Exception {
        ResponseEntity<String> response = template.withBasicAuth("admin", "admin")
                .getForEntity("/hello-world", String.class);

        assertThat(response.getStatusCode().toString()).isEqualTo("200 OK");
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }

    @Test
    public void testGreetingDefault() throws Exception {

        ResponseEntity<Greeting> response = template.withBasicAuth("admin", "admin")
                .getForEntity("/hello-world/greeting", Greeting.class);


        // Expected Greeting object
        Greeting expectedGreeting = new Greeting(2, "Hello, Stranger!");
        assertThat(response.getBody()).isEqualTo(expectedGreeting);
    }

    @Test
    public void testGreetingWithName() throws Exception {
        String url = UriComponentsBuilder.fromUriString("/hello-world/greeting")
                .queryParam("name", "Me")
                .toUriString();

        // Request the response as a Greeting object
        ResponseEntity<Greeting> response = template.withBasicAuth("admin", "admin")
                .getForEntity(url, Greeting.class);

        // Expected Greeting object
        Greeting expectedGreeting = new Greeting(1, "Hello, Me!");

        // Assert that the actual response matches the expected object
        assertThat(response.getBody()).isEqualTo(expectedGreeting);

    }
}*/
