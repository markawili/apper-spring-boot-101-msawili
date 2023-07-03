package com.msawili.samplemysqlproject.response;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class CreateAccountResponse {
    private ResponseEntity<String> response;
}
