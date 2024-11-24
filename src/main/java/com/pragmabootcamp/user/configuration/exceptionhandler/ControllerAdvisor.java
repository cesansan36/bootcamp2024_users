package com.pragmabootcamp.user.configuration.exceptionhandler;

import com.pragmabootcamp.user.adapters.exception.RoleMismatchException;
import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import com.pragmabootcamp.user.domain.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(RoleMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleRoleMismatchException(RoleMismatchException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
