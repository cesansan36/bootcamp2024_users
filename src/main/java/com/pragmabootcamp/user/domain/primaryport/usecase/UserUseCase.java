package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.domain.authentication.ITokenService;
import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.primaryport.IUserServicePort;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder, ITokenService tokenService, AuthenticationManager authenticationManager) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Token saveUser(User user) {

        if (userPersistencePort.existsByEmail(user.getEmail())) {
            // TODO custom exception
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userPersistencePort.saveUser(user);

        return new Token(tokenService.generateToken(user));
    }

    @Override
    public Token authenticateUser(User user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        User found =(User)auth.getPrincipal();

//        if (found.isEmpty()) {
//            // TODO custom exception
//            throw new RuntimeException("User not found");
//        }

        String token = tokenService.generateToken(found);

        return new Token(token);
    }

    @Override
    public void getUser(String email) {
        userPersistencePort.getUser(email);
    }
}
