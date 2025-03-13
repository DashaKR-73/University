package org.example.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(new ForwardException(ex, "UNIDENTIFIED_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreateAccountException.class)
    protected ResponseEntity<Object> handleCreateAccountException(CreateAccountException ex) {
        return new ResponseEntity<>(new ForwardException(ex, ex.getSubErrorCode()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<Object> handleLoginException(LoginException ex) {
        return new ResponseEntity<>(new ForwardException(ex, ex.getSubErrorCode()), HttpStatus.UNAUTHORIZED);
    }

}
