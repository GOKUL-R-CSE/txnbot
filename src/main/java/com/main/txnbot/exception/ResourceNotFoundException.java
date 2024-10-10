package com.main.txnbot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;

    public ResourceNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s not found with %s", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
