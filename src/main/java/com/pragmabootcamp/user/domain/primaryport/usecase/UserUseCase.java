package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.domain.authentication.ITokenService;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.primaryport.IUserServicePort;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;

    public UserUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder, ITokenService tokenService) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userPersistencePort.saveUser(user);

        return tokenService.generateToken(user);
    }

    @Override
    public void getUser(String email) {
        userPersistencePort.getUser(email);
    }
}
