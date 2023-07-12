package com.msawili.blogcapstone.exceptions;

public class EmailAlreadyRegisteredException extends Throwable{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
