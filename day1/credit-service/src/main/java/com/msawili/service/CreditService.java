package com.msawili.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    private final List<Account> accountList = new ArrayList<>();

    public Account createAccount(Double initialBalance) {
        String accountId = UUID.randomUUID().toString();

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);

        accountList.add(account);

        return account;
    }

    public List<Account> getAllAccounts() {
        return accountList;
    }

    public void addBalance(String accountId, Double amount) {
        for (Account account: accountList) {
            if (account.getId().equals(accountId)) {
                Double newBalance = account.getBalance() + amount;
                account.setBalance(newBalance);
                return;
            }
        }
    }
}
