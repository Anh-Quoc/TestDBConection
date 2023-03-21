package com.example.testdbconection.AccountsService;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;
    private String user_name;
    private String password;
}
