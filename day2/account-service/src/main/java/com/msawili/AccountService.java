package com.msawili;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private List<Account> accountList = new ArrayList<>();
    private final IdGeneratorService idGeneratorService;

    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public Account create(String firstName, String lastName, String username, String password) {
        Account account = new Account();

        String id = idGeneratorService.getNextId();
        account.setId(id);
        System.out.println("Generated ID: " + id);
        account.setCreationDate(LocalDateTime.now());
        account.setBalance(1_000.00);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(password);

        accountList.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account: accountList) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAll() {
        return accountList;
    }


}
