package com.msawili.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("credit")
public class CreditApi {
    private final CreditService creditService;

    public CreditApi(CreditService creditService) {
        this.creditService = creditService;
    }

    //create account
    @PostMapping("create")
    public Account createNewAccount(@RequestBody @NotNull CreateAccount createAccount) {
        return creditService.createAccount(createAccount.getInitialBalance());
    }

    //retrieve all accounts
    @GetMapping("getAccounts")
    public List<Account> getAllAccounts() {
        return creditService.getAllAccounts();
    }

}
