package com.pragmabootcamp.user.adapters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="user role id doesn't match endpoint purpose")
public class RoleMismatchException extends RuntimeException {

    public RoleMismatchException(String message) {
        super(message);
    }
}
