package com.pragmabootcamp.user.adapters.driving.http.rest.controller;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserRequestMapper;
import com.pragmabootcamp.user.adapters.driving.http.rest.mapper.IUserResponseMapper;
import com.pragmabootcamp.user.domain.primaryport.IUserServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/admin")
public class UserControllerAdapter {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    public UserControllerAdapter(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper, IUserResponseMapper userResponseMapper) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerAdmin(@RequestBody AddUserRequest request) {
        userServicePort.saveUser(userRequestMapper.addUserRequestToUser(request));
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserResponse> loginAdmin(@RequestBody AddUserRequest request) {
//
//    }
}
