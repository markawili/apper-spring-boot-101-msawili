package com.msawili.estore.response;

import com.msawili.estore.request.CreateUserRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class CreateUserResponse {
//    private ResponseEntity<CreateUserRequest> entity;
//    private HttpStatus status;
    private String response;
}
