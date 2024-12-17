package com.example.cosmocats.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("message", "Validation failed for object: " + ex.getMessage());
        response.put("path", "/api/products");

        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleGeneralExceptions(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());
        response.put("path", "/");

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("message", "Validation failed for object: " + ex.getBindingResult().getObjectName());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder messageBuilder = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            messageBuilder.append("Field '")
                    .append(fieldError.getField())
                    .append("' ")
                    .append(fieldError.getDefaultMessage())
                    .append(". ");
        }

        response.put("details", messageBuilder.toString());
        response.put("path", "/api/products");

        return response;
    }
}
