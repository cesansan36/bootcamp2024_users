package com.pragmabootcamp.user.testdata;

import com.pragmabootcamp.user.adapters.driven.authentication.UserAuth;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragmabootcamp.user.domain.model.Role;

import static com.pragmabootcamp.user.testdata.TestConstants.EMAIL;
import static com.pragmabootcamp.user.testdata.TestConstants.FIRST_NAME;
import static com.pragmabootcamp.user.testdata.TestConstants.ID_DOCUMENT;
import static com.pragmabootcamp.user.testdata.TestConstants.LAST_NAME;
import static com.pragmabootcamp.user.testdata.TestConstants.PASSWORD;
import static com.pragmabootcamp.user.testdata.TestConstants.PHONE_NUMBER;
import static com.pragmabootcamp.user.testdata.TestConstants.ROLE_NAME;

public class TestSecondaryData {




    public static UserAuth getUserAuth() {
        Role role = TestDomainData.getRole();
        return new UserAuth(1L, FIRST_NAME, LAST_NAME, ID_DOCUMENT, PHONE_NUMBER, EMAIL, role, PASSWORD);
    }

    public static RoleEntity getRoleEntity() {
        return new RoleEntity(1, ROLE_NAME);
    }

    public static UserEntity getUserEntity() {
        RoleEntity role = getRoleEntity();
        return new UserEntity(1L, FIRST_NAME, LAST_NAME, ID_DOCUMENT, PHONE_NUMBER, EMAIL, PASSWORD, role);
    }
}
