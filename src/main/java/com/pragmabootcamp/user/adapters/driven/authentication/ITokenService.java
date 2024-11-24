package com.pragmabootcamp.user.adapters.driven.authentication;

public interface ITokenService {

    String generateToken(UserAuth userAuthDetails);
}
