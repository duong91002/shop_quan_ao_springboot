/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 *
 * @author haidu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
   
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Map<String, Object>> handleCommonException(CommonException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", ex.getCode());
        response.put("errorMessage", ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(ex);
        response.put("errorCode", 500);
        response.put("errorMessage", "Internal Server Error!");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class) // valid request
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", HttpStatus.BAD_REQUEST.value());
        response.put("errorMessage", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NoHandlerFoundException.class) //not found
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NoHandlerFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", 404);
        response.put("errorMessage", "Page not found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) // get not found
    public ResponseEntity<Map<String, Object>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", 405);
        response.put("errorMessage", "Method " + ex.getMethod() + " is not supported for this endpoint.");
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class) //valid enum/list/set/...
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", HttpStatus.BAD_REQUEST.value());
       
        if (cause instanceof InvalidFormatException invalidFormatException) {
        	 response.put("errorMessage", "Invalid status value: " + invalidFormatException.getValue());
        } else {
            response.put("errorMessage", "Invalid request format");
        }
 
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AccessDeniedException.class) // no access
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", HttpStatus.FORBIDDEN.value());
        response.put("errorMessage", "Access Denied!");
        
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
   
}
