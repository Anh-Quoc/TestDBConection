package com.example.testdbconection.EmployeeService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @PostMapping("/employee")
    public Employee save(Employee employee){
        return employee;
    }
}
