package org.example.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleException(
            HttpClientErrorException ex) {
        return new ResponseEntity<>(ex, ex.getStatusCode());
    }
}