package com.pragmabootcamp.user.domain.model;

import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import com.pragmabootcamp.user.domain.util.DomainConstants;

public class Token {

    private final String value;

    public Token(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {

        if (value == null || value.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.TOKEN_FIELD));
        }
    }

    public String getValue() {
        return value;
    }
}
