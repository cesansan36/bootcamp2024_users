package com.pragmabootcamp.user.domain.model;

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
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.password = password;
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
