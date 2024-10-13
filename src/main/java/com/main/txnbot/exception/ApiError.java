package com.main.txnbot.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ApiError(HttpStatus status, String message, String resourceName, String fieldName) {
        this.status = status;
        this.message = message;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    // Getters and setters
    // ...
}
