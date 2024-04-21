package com.pragmabootcamp.user.testdata;

import com.pragmabootcamp.user.domain.model.Role;
import com.pragmabootcamp.user.domain.model.User;

import static com.pragmabootcamp.user.testdata.TestConstants.EMAIL;
import static com.pragmabootcamp.user.testdata.TestConstants.FIRST_NAME;
import static com.pragmabootcamp.user.testdata.TestConstants.ID_DOCUMENT;
import static com.pragmabootcamp.user.testdata.TestConstants.LAST_NAME;
import static com.pragmabootcamp.user.testdata.TestConstants.PASSWORD;
import static com.pragmabootcamp.user.testdata.TestConstants.PHONE_NUMBER;
import static com.pragmabootcamp.user.testdata.TestConstants.ROLE_NAME;

public class TestDomainData {

    public static Role getRole() {
        return new Role(1, ROLE_NAME);
    }

    public static User getUser() {
        return new User(1L,FIRST_NAME, LAST_NAME, ID_DOCUMENT, PHONE_NUMBER, EMAIL, getRole(), PASSWORD);
    }
}
