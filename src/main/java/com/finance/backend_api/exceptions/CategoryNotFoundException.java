package com.finance.backend_api.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String msg){
        super(msg);
    }
}
