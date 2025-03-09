package com.example.javaspring.repository;

import com.example.javaspring.model.Employee;
import com.example.javaspring.model.EmployeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRecordRepository extends JpaRepository<EmployeeRecord, Long> {

}