package com.pragmabootcamp.user.domain.secondaryport;

import com.pragmabootcamp.user.domain.model.User;

public interface IUserAuthServ {

    String saveUser(User user);
    String authenticateUser(User user);
}
