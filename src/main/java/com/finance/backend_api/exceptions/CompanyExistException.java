package com.finance.backend_api.exceptions;

public class CompanyExistException extends RuntimeException {

    public CompanyExistException(String msg){
        super(msg);
    }
}
