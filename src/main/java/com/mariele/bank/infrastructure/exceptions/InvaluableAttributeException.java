package com.mariele.bank.infrastructure.exceptions;

public class InvaluableAttributeException extends RuntimeException {
    public InvaluableAttributeException(String message) {
        super(message);
    }

    public InvaluableAttributeException(String message, Throwable cause) {
        super(message, cause);
    }
}
