package com.pragmabootcamp.user.domain.model;

import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {


    @Test
    @DisplayName("Regular behaviour")
    void regularBehaviour() {
        Role role = new Role(1, "ADMINISTRATOR");

        assertEquals(1, role.getId());
        assertEquals("ADMINISTRATOR", role.getName());
    }

    @Test
    @DisplayName("should fail on id null")
    void shouldFailOnIdNull() {
        assertThrows(EmptyFieldException.class, () -> {
            new Role(null, "ADMINISTRATOR");
        });
    }

    @Test
    @DisplayName("should fail on name null")
    void shouldFailOnNameNull() {
        assertThrows(EmptyFieldException.class, () -> {
            new Role(1, null);
        });
    }

    @Test
    @DisplayName("should fail on name empty")
    void shouldFailOnNameEmpty() {
        assertThrows(EmptyFieldException.class, () -> {
            new Role(1, "");
        });
    }
}
