package com.msawili;

public class AccountNotFoundException extends Throwable {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
