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
    public ResponseEntity<String> registerAdmin(@RequestBody AddUserRequest request) {
        return ResponseEntity.ok(userServicePort.saveUser(userRequestMapper.addUserRequestToUser(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin() {
        return ResponseEntity.ok("✨ こんにちわ");
    }
}
