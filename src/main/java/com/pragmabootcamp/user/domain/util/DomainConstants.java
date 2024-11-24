package com.pragmabootcamp.user.domain.util;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum UserField{
        ID,
        FIRST_NAME,
        LAST_NAME,
        ID_DOCUMENT,
        PHONE_NUMBER,
        EMAIL,
        ROLE,
        PASSWORD
    }
    public enum RoleField{
        ID,
        NAME
    }
    public static final String TOKEN_FIELD = "token";

    public static final String FIELD_EMPTY_MESSAGE = "The field %s cannot be empty";
    public static final String USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user you are trying to add already exists";
}
