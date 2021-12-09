package com.service.transfer.money.exception;

public class ErrorTransfer extends RuntimeException{
    public ErrorTransfer(String message) {
        super(message);
    }
}
