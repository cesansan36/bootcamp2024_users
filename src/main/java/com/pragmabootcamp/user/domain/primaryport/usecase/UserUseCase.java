package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.adapters.authentication.IUserAuthServ;
import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;

public class UserUseCase implements IUserPrimaryPort {

    private final IUserAuthServ userAuthServ;
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserAuthServ userAuthServ, IUserPersistencePort userPersistencePort) {
        this.userAuthServ = userAuthServ;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Token saveUser(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            // TODO custom exception
            throw new RuntimeException("User already exists");
        }

        String token = userAuthServ.saveUser(user);
        return new Token(token);
    }

    @Override
    public Token authenticateUser(User user) {
        String token = userAuthServ.authenticateUser(user);
        return new Token(token);
    }
}
