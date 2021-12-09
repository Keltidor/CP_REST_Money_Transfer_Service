package com.service.transfer.money.exception;

public class ErrorInputData extends RuntimeException{
    public ErrorInputData(String message) {
        super(message);
    }
}
