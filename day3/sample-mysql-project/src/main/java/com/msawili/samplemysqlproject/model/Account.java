package com.msawili.samplemysqlproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double balance;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;

//    @NotBlank(message = "First name is required")
    private String firstName;
//    @NotBlank(message = "Last name is required")
    private String lastName;
//    @NotBlank(message = "Email is required")
    private String email;
//    @NotBlank(message = "Password is required")
    private String password;
}
