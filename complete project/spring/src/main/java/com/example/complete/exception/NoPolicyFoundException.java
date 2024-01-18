package com.example.complete.exception;

public class NoPolicyFoundException extends RuntimeException {

    public NoPolicyFoundException(String message) {
        super(message);
    }

    public NoPolicyFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
