package com.msawili;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private String id;
    private Double balance;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;

    private String firstName;
    private String lastName;
    private String username;
    private String clearPassword;
}
