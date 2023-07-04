package com.msawili.samplemysqlproject.service;

import com.msawili.samplemysqlproject.model.Account;

import java.util.List;

public interface AccountService {
    public Account createAccount(Account account);
    public List<Account> getAccounts();
    public Account getAccountById(String id);
}
