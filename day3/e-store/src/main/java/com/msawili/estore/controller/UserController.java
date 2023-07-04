package com.msawili.estore.controller;

import com.msawili.estore.exception.InvalidUserAgeException;
import com.msawili.estore.request.CreateUserRequest;
import com.msawili.estore.response.CreateUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        System.out.println(request);
        CreateUserResponse response = new CreateUserResponse();
        response.setResponse("User created successfully");
        return response;
    }
}
