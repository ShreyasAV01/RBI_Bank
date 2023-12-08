package com.example.loans.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException {
    public ResourceNotfoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
