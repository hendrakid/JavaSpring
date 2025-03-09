package com.example.javaspring.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_record")
public class EmployeeRecord {

    private @Id
    @GeneratedValue Long id;
    private Long employeeId;
    private LocalDateTime recordDate;


    public EmployeeRecord() {}

    public EmployeeRecord(Long employeeId, LocalDateTime recordDate) {

        this.employeeId = employeeId;
        this.recordDate = recordDate;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}