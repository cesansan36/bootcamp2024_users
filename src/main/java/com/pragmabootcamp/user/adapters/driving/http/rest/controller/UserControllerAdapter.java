package com.pragmabootcamp.user.adapters.driving.http.rest.controller;

import com.pragmabootcamp.user.adapters.exception.RoleMismatchException;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.LoginUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserRequestMapper;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserResponseMapper;
import com.pragmabootcamp.user.adapters.driving.http.rest.util.ControllerAdapterConstants;
import com.pragmabootcamp.user.domain.primaryport.IUserPrimaryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Register an admin", description = "Register an admin\n\nNo auth required")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody AddUserRequest request) {

        if (!request.getRoleId().equals(ControllerAdapterConstants.ADMIN_ID)) {
            throw new RoleMismatchException(ControllerAdapterConstants.ROLE_MISMATCH_MESSAGE);
        }

        UserResponse response = userResponseMapper.toUserResponse(
                                    userServicePort.saveUser(
                                            userRequestMapper.addUserRequestToUser(request, ControllerAdapterConstants.ADMIN_ID)));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register/tutor")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Register a tutor", description = "Register a tutor\n\nCan only be used by admins")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponse> registerTutor(@RequestBody AddUserRequest request) {

        if (!request.getRoleId().equals(ControllerAdapterConstants.TUTOR_ID)) {
            throw new RoleMismatchException(ControllerAdapterConstants.ROLE_MISMATCH_MESSAGE);
        }

        UserResponse response = userResponseMapper.toUserResponse(
                userServicePort.saveUser(
                        userRequestMapper.addUserRequestToUser(request, ControllerAdapterConstants.TUTOR_ID)));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register/student")
    @Operation(summary = "Register a student", description = "Register a tutor\n\nCan only be used by admins or tutors")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TUTOR')")
    public ResponseEntity<UserResponse> registerStudent(@RequestBody AddUserRequest request) {

        if (!request.getRoleId().equals(ControllerAdapterConstants.STUDENT_ID)) {
            throw new RoleMismatchException(ControllerAdapterConstants.ROLE_MISMATCH_MESSAGE);
        }

        UserResponse response = userResponseMapper.toUserResponse(
                userServicePort.saveUser(
                        userRequestMapper.addUserRequestToUser(request, ControllerAdapterConstants.STUDENT_ID)));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "No auth required")
    public ResponseEntity<UserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(
                userResponseMapper.toUserResponse(
                        userServicePort.authenticateUser(
                                userRequestMapper.loginUserRequestToUser(request))));
    }

    @PostMapping("/validate/restricted")
    @Operation(summary = "Validate restricted", description = "Checks if user is admin")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> validateRestricted() {
        return ResponseEntity.ok(true);
    }
    @PostMapping("/validate/any")
    @Operation(summary = "Validate any", description = "Checks if user is admin, tutor or student")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TUTOR') or hasRole('ROLE_STUDENT')")
    public ResponseEntity<Boolean> validateAny() {
        return ResponseEntity.ok(true);
    }

    @PostMapping("/test")
    @Operation(summary = "Test endpoint", description = "No auth required")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("access granted こんにちは 🍻");
    }
}
