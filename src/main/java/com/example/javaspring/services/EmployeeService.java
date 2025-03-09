package com.example.javaspring.services;


import com.example.javaspring.exception.EmployeeNotFoundException;
import com.example.javaspring.model.Employee;
import com.example.javaspring.model.MessageKafka;
import com.example.javaspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    // Create User
    public Employee createUser(Employee user) {
        Employee data =  employeeRepository.save(user);
        return data;
    }

    // Get All Users
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    // Get User by ID
    public Optional<Employee> getUserById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update User
    public Employee updateUser(Long id, Employee updatedUser) {
        return employeeRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            return employeeRepository.save(user);
        }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // Delete User
    public void deleteUser(Long id) {
        employeeRepository.deleteById(id);
    }
}
