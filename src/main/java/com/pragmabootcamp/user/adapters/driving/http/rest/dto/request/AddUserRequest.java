package com.pragmabootcamp.user.adapters.driving.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddUserRequest {

    private String firstName;
    private String lastName;
    private Long idDocument;
    private Long phoneNumber;
    private String email;
    private Integer roleId;
    private String password;

}
