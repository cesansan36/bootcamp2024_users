package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.primaryport.IUserServicePort;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userPersistencePort.saveUser(user);
    }

    @Override
    public void getUser(String email) {
        userPersistencePort.getUser(email);
    }
}
