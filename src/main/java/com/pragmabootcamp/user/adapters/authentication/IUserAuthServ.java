package com.pragmabootcamp.user.adapters.authentication;

import com.pragmabootcamp.user.domain.model.Token;

public interface IUserAuthServ {

    Token saveUser(UserAuth userAuth);
    Token authenticateUser(UserAuth userAuth);
    void getUser(String email);
}
