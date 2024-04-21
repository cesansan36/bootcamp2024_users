package com.pragmabootcamp.user.adapters.driving.http.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragmabootcamp.user.adapters.exception.RoleMismatchException;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.LoginUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserRequestMapper;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserResponseMapper;
import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.primaryport.IUserPrimaryPort;
import com.pragmabootcamp.user.testdata.TestConstants;
import com.pragmabootcamp.user.testdata.TestControllerData;
import com.pragmabootcamp.user.testdata.TestDomainData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerAdapterTest {

    private UserControllerAdapter userControllerAdapter;

    private IUserPrimaryPort userServicePort;
    private IUserRequestMapper userRequestMapper;
    private IUserResponseMapper userResponseMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userServicePort = mock(IUserPrimaryPort.class);
        userRequestMapper = mock(IUserRequestMapper.class);
        userResponseMapper = mock(IUserResponseMapper.class);
        userControllerAdapter = new UserControllerAdapter(userServicePort, userRequestMapper, userResponseMapper);

        mockMvc = MockMvcBuilders.standaloneSetup(userControllerAdapter).build();
    }

    @ParameterizedTest
    @MethodSource("successfulRegisterData")
    @DisplayName("Register Admin success")
    void registerAdmin(Integer requestRoleId, String endpoint) throws Exception {
        String inputJson = getAddRequestObject(requestRoleId);

        User user = TestDomainData.getUser();
        Token token = new Token(TestConstants.TOKEN);
        UserResponse userResponse = TestControllerData.getUserResponse();

        when(userRequestMapper.addUserRequestToUser(any(AddUserRequest.class), anyInt())).thenReturn(user);
        when(userServicePort.saveUser(any(User.class))).thenReturn(token);
        when(userResponseMapper.toUserResponse(any(Token.class))).thenReturn(userResponse);

        MockHttpServletRequestBuilder request = post("/users/register/" + endpoint).contentType(MediaType.APPLICATION_JSON).content(inputJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value(TestConstants.TOKEN));

        assertAll(
                () -> verify(userRequestMapper, times(1)).addUserRequestToUser(any(AddUserRequest.class), anyInt()),
                () -> verify(userServicePort, times(1)).saveUser(any(User.class)),
                () -> verify(userResponseMapper, times(1)).toUserResponse(any(Token.class))
        );
    }

    @ParameterizedTest
    @MethodSource("idMismatchData")
    @DisplayName("Register Admin Fail because role id mismatch the endpoint purpose")
    @ExceptionHandler(IllegalArgumentException.class)
    void registerAdminFail(Integer requestRoleId, String endpoint) throws Exception {
        String inputJson = getAddRequestObject(requestRoleId);

        MockHttpServletRequestBuilder request = post("/users/register/" + endpoint).contentType(MediaType.APPLICATION_JSON).content(inputJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(RoleMismatchException.class, result.getResolvedException()));

        assertAll(
                () -> verify(userRequestMapper, times(0)).addUserRequestToUser(any(AddUserRequest.class), anyInt()),
                () -> verify(userServicePort, times(0)).saveUser(any(User.class)),
                () -> verify(userResponseMapper, times(0)).toUserResponse(any(Token.class))
        );
    }

    @Test
    void login() throws Exception {
        Object inputObject = new Object() {
            public final String email = TestConstants.EMAIL;
            public final String password = TestConstants.PASSWORD;
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(inputObject);

        User user = TestDomainData.getUser();
        Token token = new Token(TestConstants.TOKEN);
        UserResponse userResponse = TestControllerData.getUserResponse();

        when(userRequestMapper.loginUserRequestToUser(any(LoginUserRequest.class))).thenReturn(user);
        when(userServicePort.authenticateUser(any(User.class))).thenReturn(token);
        when(userResponseMapper.toUserResponse(any(Token.class))).thenReturn(userResponse);

        MockHttpServletRequestBuilder request = post("/users/login").contentType(MediaType.APPLICATION_JSON).content(inputJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(TestConstants.TOKEN));
    }

    @Test
    void validateRestricted() throws Exception {
        MockHttpServletRequestBuilder request = post("/users/validate/restricted");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    void validateAny() throws Exception {
        MockHttpServletRequestBuilder request = post("/users/validate/any");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    private static String getAddRequestObject(Integer idRole) throws JsonProcessingException {
        Object inputObject = new Object() {
            public final String firstName = TestConstants.FIRST_NAME;
            public final String lastName = TestConstants.LAST_NAME;
            public final Long idDocument = TestConstants.ID_DOCUMENT;
            public final Long phoneNumber = TestConstants.PHONE_NUMBER;
            public final String email = TestConstants.EMAIL;
            public final Integer roleId = idRole;
            public final String password = TestConstants.PASSWORD;
        };
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(inputObject);
    }

    static Stream<Arguments> successfulRegisterData() {
        return Stream.of(
                Arguments.of(TestConstants.ADMIN_ID, "admin"),
                Arguments.of(TestConstants.TUTOR_ID, "tutor"),
                Arguments.of(TestConstants.STUDENT_ID, "student")
        );
    }

    static Stream<Arguments> idMismatchData() {
        return Stream.of(
                Arguments.of(TestConstants.TUTOR_ID, "admin"),
                Arguments.of(TestConstants.STUDENT_ID, "admin"),
                Arguments.of(TestConstants.ADMIN_ID, "tutor"),
                Arguments.of(TestConstants.STUDENT_ID, "tutor"),
                Arguments.of(TestConstants.ADMIN_ID, "student"),
                Arguments.of(TestConstants.TUTOR_ID, "student")
        );
    }
}
