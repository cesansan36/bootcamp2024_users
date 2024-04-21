package com.pragmabootcamp.user.domain.model;

import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TokenTest {

    @Test
    @DisplayName("Regular behaviour")
    void regularBehaviour() {
        Token token = new Token("token");

        assertEquals("token", token.getValue());
    }

    @Test
    @DisplayName("should fail on value null")
    void shouldFailOnValueNull() {
        assertThrows(EmptyFieldException.class, () -> {
            new Token(null);
        });
    }

    @Test
    @DisplayName("should fail on value empty")
    void shouldFailOnValueEmpty() {
        assertThrows(EmptyFieldException.class, () -> {
            new Token(null);
        });
    }
}
