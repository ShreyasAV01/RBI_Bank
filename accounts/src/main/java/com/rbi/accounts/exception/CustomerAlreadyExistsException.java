package com.rbi.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//custom exception
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
