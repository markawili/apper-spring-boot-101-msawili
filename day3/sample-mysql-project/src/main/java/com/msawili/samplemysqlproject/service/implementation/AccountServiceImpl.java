package com.msawili.samplemysqlproject.service.implementation;

import com.msawili.samplemysqlproject.model.Account;
import com.msawili.samplemysqlproject.repository.AccountRepository;
import com.msawili.samplemysqlproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findById(id).orElse(null);
    }
}
