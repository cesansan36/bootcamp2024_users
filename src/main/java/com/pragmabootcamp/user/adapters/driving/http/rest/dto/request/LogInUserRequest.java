package com.pragmabootcamp.user.adapters.driving.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LogInUserRequest {

    private String email;
    private String password;
}
