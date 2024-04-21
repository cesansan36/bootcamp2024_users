package com.pragmabootcamp.user.testdata;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.LoginUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;

public class TestControllerData {

    public static AddUserRequest getAddUserRequest(Integer roleId) {
        return new AddUserRequest(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestConstants.ID_DOCUMENT, TestConstants.PHONE_NUMBER, TestConstants.EMAIL, roleId, TestConstants.PASSWORD);
    }

    public static UserResponse getUserResponse() {
        return new UserResponse(TestConstants.TOKEN);
    }

    public static LoginUserRequest getLoginUserRequest() {
        return new LoginUserRequest(TestConstants.EMAIL, TestConstants.PASSWORD);
    }
}
