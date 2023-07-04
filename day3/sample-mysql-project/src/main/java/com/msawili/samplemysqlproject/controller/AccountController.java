package com.msawili.samplemysqlproject.controller;

import com.msawili.samplemysqlproject.model.Account;
import com.msawili.samplemysqlproject.request.CreateAccountRequest;
import com.msawili.samplemysqlproject.response.CreateAccountResponse;
import com.msawili.samplemysqlproject.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("saveAccount")
    public CreateAccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        account.setCreationDate(LocalDateTime.now());
        account.setBalance(1_000_000.0);
        accountService.createAccount(account);

        CreateAccountResponse response = new CreateAccountResponse();
        response.setResponse(ResponseEntity.ok("Account created"));
        return response;
    }

    @GetMapping("getAccounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
