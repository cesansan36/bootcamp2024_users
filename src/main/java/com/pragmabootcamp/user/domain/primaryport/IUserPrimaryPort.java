package com.pragmabootcamp.user.domain.primaryport;

import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;

public interface IUserPrimaryPort {
    Token saveUser(User user);

    Token authenticateUser(User user);
}
