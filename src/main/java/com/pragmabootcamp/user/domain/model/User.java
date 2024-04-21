package com.pragmabootcamp.user.domain.model;

import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import com.pragmabootcamp.user.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class User {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Long idDocument;
    private final Long phoneNumber;
    private final String email;
    private final Role role;
    private final String password;

    public User(Long id, String firstName, String lastName, Long idDocument, Long phoneNumber, String email, Role role, String password) {
        validate(id, firstName, lastName, idDocument, phoneNumber, email, role, password);

        this.id = id;
        this.firstName = requireNonNull(firstName, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.FIRST_NAME));
        this.lastName = requireNonNull(lastName, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.LAST_NAME));
        this.idDocument = requireNonNull(idDocument, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.ID_DOCUMENT));
        this.phoneNumber = requireNonNull(phoneNumber, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.PHONE_NUMBER));
        this.email = requireNonNull(email, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.EMAIL));
        this.role = requireNonNull(role, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.ROLE));
        this.password = requireNonNull(password, DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.PASSWORD));
    }

    private void validate(Long id, String firstName, String lastName, Long idDocument, Long phoneNumber, String email, Role role, String password) {

        if (id == null ) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.ID));
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.FIRST_NAME));
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.LAST_NAME));
        }
        if (idDocument == null ) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.ID_DOCUMENT));
        }
        if (phoneNumber == null ) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.PHONE_NUMBER));
        }
        if (email == null || email.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.EMAIL));
        }
        if (role == null ) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.ROLE));
        }
        if (password == null || password.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.UserField.PASSWORD));
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getIdDocument() {
        return idDocument;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

}
