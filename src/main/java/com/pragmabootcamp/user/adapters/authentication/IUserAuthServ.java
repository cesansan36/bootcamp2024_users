package com.pragmabootcamp.user.adapters.authentication;

import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;

public interface IUserAuthServ {

    String saveUser(User user);
    String authenticateUser(User user);
//    void getUser(String email);
}
