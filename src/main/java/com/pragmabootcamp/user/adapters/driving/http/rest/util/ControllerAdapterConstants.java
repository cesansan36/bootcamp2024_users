package com.pragmabootcamp.user.adapters.driving.http.rest.util;

public class ControllerAdapterConstants {

    private ControllerAdapterConstants() {
        throw new IllegalStateException("Utility class");
    }


    public static final int ADMIN_ID = 1;
    public static final int TUTOR_ID = 2;
    public static final int STUDENT_ID = 3;

    public static final String ROLE_MISMATCH_MESSAGE = "You are trying to add an user with a role that doesn't match the ones allowed in this module";
}
