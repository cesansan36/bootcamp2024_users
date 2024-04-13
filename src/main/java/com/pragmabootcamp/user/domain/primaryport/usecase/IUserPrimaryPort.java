package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.adapters.authentication.UserAuth;
import com.pragmabootcamp.user.domain.model.Token;

public interface IUserPrimaryPort {
    Token saveUser(UserAuth userAuth);

    Token authenticateUser(UserAuth userAuth);
}
