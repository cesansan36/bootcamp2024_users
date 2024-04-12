package com.pragmabootcamp.user.domain.secondaryport;

import com.pragmabootcamp.user.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);

    boolean existsByEmail(String email);
    Optional<User> getUser(String email);
}
