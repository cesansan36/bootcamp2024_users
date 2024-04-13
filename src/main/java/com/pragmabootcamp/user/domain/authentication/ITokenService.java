package com.pragmabootcamp.user.domain.authentication;


import com.pragmabootcamp.user.adapters.authentication.UserAuth;

public interface ITokenService {

    String generateToken(UserAuth userAuthDetails);
}
