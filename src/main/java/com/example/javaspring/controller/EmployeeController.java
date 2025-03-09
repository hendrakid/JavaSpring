package com.example.javaspring.controller;


import java.util.List;
import java.util.Optional;

import com.example.javaspring.model.Employee;
import com.example.javaspring.services.EmployeeService;
import com.example.javaspring.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private KafkaProducer kafkaProducer;

    // Create User
    @PostMapping
    public ResponseEntity<Employee> createUser(@RequestBody Employee user) {
        Employee savedUser = employeeService.createUser(user);

        // Send message to Kafka after user creation
        kafkaProducer.sendMessage("employee", savedUser);

        return ResponseEntity.ok(savedUser);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers() {
        return ResponseEntity.ok(employeeService.getAllUsers());
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getUserById(id));
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateUser(@PathVariable Long id, @RequestBody Employee user) {
        return ResponseEntity.ok(employeeService.updateUser(id, user));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        employeeService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}