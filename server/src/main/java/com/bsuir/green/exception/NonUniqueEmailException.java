package com.bsuir.green.exception;

public class NonUniqueEmailException extends Exception {
    public NonUniqueEmailException(String errorMessage) {
        super(errorMessage);
    }
}
