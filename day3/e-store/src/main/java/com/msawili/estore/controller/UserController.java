package com.msawili.estore.controller;

import com.msawili.estore.exception.InvalidUserAgeException;
import com.msawili.estore.payload.request.CreateUserRequest;
import com.msawili.estore.payload.response.CreateUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());
        LocalDate now = LocalDate.now();
        int age = now.getYear() - birthDate.getYear();
        if (age < 15) {
            throw new InvalidUserAgeException("User's age must be 15 or above");
        }

        System.out.println(request);
        CreateUserResponse response = new CreateUserResponse();
        response.setResponse("User created successfully");
        return response;
    }
}
