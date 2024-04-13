package com.pragmabootcamp.user.adapters.driving.http.rest.controller;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.LoginUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserRequestMapper;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserResponseMapper;
import com.pragmabootcamp.user.adapters.driving.http.rest.util.ControllerAdapterConstants;
import com.pragmabootcamp.user.adapters.authentication.IUserAuthServ;
import com.pragmabootcamp.user.domain.primaryport.usecase.IUserPrimaryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserControllerAdapter {

    private final IUserPrimaryPort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    public UserControllerAdapter(IUserPrimaryPort userServicePort, IUserRequestMapper userRequestMapper, IUserResponseMapper userResponseMapper) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody AddUserRequest request) {

        if (!request.getRoleId().equals(ControllerAdapterConstants.ADMIN_ID)) {
            throw new IllegalArgumentException(ControllerAdapterConstants.ROLE_MISMATCH_MESSAGE);
        }

        return ResponseEntity.ok(
                userResponseMapper.toUserResponse(
                        userServicePort.saveUser(
                                userRequestMapper.addUserRequestToUser(request, ControllerAdapterConstants.ADMIN_ID))));
    }

    @PostMapping("/register/tutor")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserResponse> registerTutor(@RequestBody AddUserRequest request) {

        if (!request.getRoleId().equals(ControllerAdapterConstants.TUTOR_ID)) {
            throw new IllegalArgumentException(ControllerAdapterConstants.ROLE_MISMATCH_MESSAGE);
        }

        return ResponseEntity.ok(
                userResponseMapper.toUserResponse(
                        userServicePort.saveUser(
                                userRequestMapper.addUserRequestToUser(request, ControllerAdapterConstants.TUTOR_ID))));
    }

    @PostMapping("/register/student")
    @Secured({"ROLE_ADMIN", "ROLE_TUTOR"})
    public ResponseEntity<UserResponse> registerStudent(@RequestBody AddUserRequest request) {

        if (!request.getRoleId().equals(ControllerAdapterConstants.STUDENT_ID)) {
            throw new IllegalArgumentException(ControllerAdapterConstants.ROLE_MISMATCH_MESSAGE);
        }

        return ResponseEntity.ok(
                userResponseMapper.toUserResponse(
                        userServicePort.saveUser(
                                userRequestMapper.addUserRequestToUser(request, ControllerAdapterConstants.STUDENT_ID))));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(
                userResponseMapper.toUserResponse(
                        userServicePort.authenticateUser(
                                userRequestMapper.loginUserRequestToUser(request))));
    }

    @PostMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("access granted こんにちは 🍻");
    }
}
