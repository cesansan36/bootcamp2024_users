package com.pragmabootcamp.user.adapters.driving.http.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Long idDocument;
    private Long phoneNumber;
    private String email;
    private String role;
}
