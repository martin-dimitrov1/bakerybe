package com.example.bakery.exception;

import java.time.LocalDateTime;

public record ErrorResponse(String error,
                            ErrorType type,
                            LocalDateTime timestamp) {
    public ErrorResponse(String error, ErrorType type) {
        this(error, type, LocalDateTime.now());
    }

    public enum ErrorType {
        SERVICE_CONNECTION, VALIDATION, ERROR
    }

    public record ValidationError(String name,
                                  String error) {
    }
}
