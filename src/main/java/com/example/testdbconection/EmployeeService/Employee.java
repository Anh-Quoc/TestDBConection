package com.example.testdbconection.EmployeeService;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    private String name;
    private String email;
    private int age;
    @Id
    private int employee_id;


    public void setId(int id) {
        this.employee_id = id;
    }

    public int getId() {
        return employee_id;
    }
}
