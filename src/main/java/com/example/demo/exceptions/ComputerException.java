package com.example.demo.exceptions;

public class ComputerException extends RuntimeException{
    public ComputerException() {
        super();
    }

    public ComputerException(String message) {
        super(message);
    }

    public ComputerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComputerException(Throwable cause) {
        super(cause);
    }

    protected ComputerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
