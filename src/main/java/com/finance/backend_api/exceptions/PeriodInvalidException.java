package com.finance.backend_api.exceptions;

public class PeriodInvalidException extends RuntimeException {
    public PeriodInvalidException(String message) {
        super(message);
    }
}
