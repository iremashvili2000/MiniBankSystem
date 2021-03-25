package com.example.demo.exceptions;

public class DontFoundException extends RuntimeException{
    public DontFoundException() {
        super();
    }

    public DontFoundException(String message) {
        super(message);
    }

    public DontFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontFoundException(Throwable cause) {
        super(cause);
    }

    protected DontFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
