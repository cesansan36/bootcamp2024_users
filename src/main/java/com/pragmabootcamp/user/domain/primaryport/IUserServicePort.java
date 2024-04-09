package com.pragmabootcamp.user.domain.primaryport;

import com.pragmabootcamp.user.domain.model.User;

public interface IUserServicePort {

    void saveUser(User user);
    void getUser(String email);
}
