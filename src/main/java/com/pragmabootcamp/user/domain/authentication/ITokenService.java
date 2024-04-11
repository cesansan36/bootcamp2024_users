package com.pragmabootcamp.user.domain.authentication;


import com.pragmabootcamp.user.domain.model.User;

public interface ITokenService {

    String generateToken(User userDetails);
}
