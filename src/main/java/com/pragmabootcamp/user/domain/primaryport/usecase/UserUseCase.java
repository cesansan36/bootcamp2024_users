package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.adapters.authentication.IUserAuthServ;
import com.pragmabootcamp.user.adapters.authentication.UserAuth;
import com.pragmabootcamp.user.domain.model.Token;

public class UserUseCase implements IUserPrimaryPort {

    private final IUserAuthServ userAuthServ;

    public UserUseCase(IUserAuthServ userAuthServ) {
        this.userAuthServ = userAuthServ;
    }

    @Override
    public Token saveUser(UserAuth userAuth) {
        return new Token("token");
    }

    @Override
    public Token authenticateUser(UserAuth userAuth) {
        return new Token("token");
    }
}
