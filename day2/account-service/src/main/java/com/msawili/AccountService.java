package com.msawili;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AccountService {

    private List<Account> accountList = new ArrayList<>();
    private final IdGeneratorService idGeneratorService;

    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public Account create(String firstName, String lastName, String username, String password) throws UsernameAlreadyRegisteredException {
        Account account = new Account();

        String id = idGeneratorService.getNextId();

        Account isAccountOld = accountList.stream()
                .filter(acc -> acc.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(isAccountOld)) {
            account.setId(id);

            account.setCreationDate(LocalDateTime.now());
            account.setBalance(1_000.00);

            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setUsername(username);
            account.setClearPassword(password);

            accountList.add(account);

            return account;
        } else {
            throw new UsernameAlreadyRegisteredException("Username already taken!");
        }
    }

    public Account get(String accountId) throws AccountNotFoundException {
        for (Account account: accountList) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found!");
    }

    public List<Account> getAll() {
        return accountList;
    }

    public LocalDateTime update(String accountId, String firstName, String lastName, String username, String password) throws AccountNotFoundException {
        Account account = get(accountId);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(password);
        LocalDateTime timeUpdated = LocalDateTime.now();
        account.setLastUpdated(timeUpdated);

        accountList.set(accountList.indexOf(account), account);

        return timeUpdated;
    }

    public void delete(String accountId) throws AccountNotFoundException {
        accountList.remove(get(accountId));
    }
}
