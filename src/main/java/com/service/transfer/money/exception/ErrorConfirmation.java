package com.service.transfer.money.exception;

public class ErrorConfirmation extends RuntimeException{
    public ErrorConfirmation(String message) {
        super(message);
    }
}
