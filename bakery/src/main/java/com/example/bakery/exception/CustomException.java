package com.example.bakery.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final HttpStatus status;

    public CustomException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public CustomException(HttpStatus status, String s) {
        super(s);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
