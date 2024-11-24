package com.pragmabootcamp.user.domain.secondaryport;

import com.pragmabootcamp.user.adapters.driven.authentication.UserAuth;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(UserAuth userAuth);

    boolean existsByEmail(String email);
    Optional<UserAuth> getUser(String email);
}
