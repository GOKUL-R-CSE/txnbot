package com.main.txnbot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class ResourceAlreadyExistsException extends RuntimeException{

    private String resourceName;
    private String fieldName;

    public ResourceAlreadyExistsException(String resourceName, String fieldName) {
        super(String.format("%s already exists with %s", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
