package com.pragmabootcamp.user.domain.secondaryport;

import com.pragmabootcamp.user.domain.model.User;

public interface IUserPersistencePort {

    void saveUser(User user);
    User getUser(String email);
}
