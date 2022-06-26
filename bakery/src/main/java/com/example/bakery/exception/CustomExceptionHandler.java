package com.example.bakery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleConnectionExceptions(RuntimeException ex) {
        ex.printStackTrace();
        String errorMsg = ex.getMessage();
        log.warn("Error thrown : " + errorMsg);
        ErrorResponse errorResponse = new ErrorResponse(errorMsg, ErrorResponse.ErrorType.SERVICE_CONNECTION);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<ErrorResponse.ValidationError> list = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            list.add(new ErrorResponse.ValidationError(fieldName, errorMessage));
        });
        ErrorResponse response = new ErrorResponse(list.toString(), ErrorResponse.ErrorType.VALIDATION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomExceptions(CustomException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ErrorResponse.ErrorType.ERROR);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
