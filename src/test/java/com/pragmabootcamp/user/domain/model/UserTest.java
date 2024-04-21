package com.pragmabootcamp.user.domain.model;

import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import com.pragmabootcamp.user.testdata.TestDomainData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UserTest {

    @Test
    @DisplayName("Regular behaviour")
    void regularBehaviour() {
        Role role = TestDomainData.getRole();
        User technology = new User(1L, "John", "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", role, "this one");

        assertAll(
                () -> assertEquals(1L, technology.getId()),
                () -> assertEquals("John", technology.getFirstName()),
                () -> assertEquals("Doe", technology.getLastName()),
                () -> assertEquals(1234567890L, technology.getIdDocument()),
                () -> assertEquals(1234567890L, technology.getPhoneNumber()),
                () -> assertEquals("admin@yimeil.com", technology.getEmail()),
                () -> assertEquals(role.getName(), technology.getRole().getName()),
                () -> assertEquals("this one", technology.getPassword())
        );
    }

    @ParameterizedTest
    @DisplayName("should fail on empty or null fields")
    @MethodSource("dataWithEmptyOrNullFields")
    void shouldFailOnEmptyOrNullFields(Long id, String firstName, String lastName, Long idDocument, Long phoneNumber, String email, Role role, String password) {

        assertThrows(EmptyFieldException.class, () -> {
            new User(id, firstName, lastName, idDocument, phoneNumber, email, role, password);
        });
    }

    static Stream<Arguments> dataWithEmptyOrNullFields() {
        return Stream.of(
                Arguments.of(null, "John", "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, null, "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "", "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", null, 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", "", 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", "Doe", null, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", "Doe", 1234567890L, null, "admin@yimeil.com", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", "Doe", 1234567890L, 1234567890L, null, TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", "Doe", 1234567890L, 1234567890L, "", TestDomainData.getRole(), "this one"),
                Arguments.of(1L, "John", "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", null, "this one"),
                Arguments.of(1L, "John", "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), null),
                Arguments.of(1L, "John", "Doe", 1234567890L, 1234567890L, "admin@yimeil.com", TestDomainData.getRole(), "")
        );
    }
}
