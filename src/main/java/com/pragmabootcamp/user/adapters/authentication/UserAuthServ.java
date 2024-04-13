package com.pragmabootcamp.user.adapters.authentication;

import com.pragmabootcamp.user.domain.authentication.ITokenService;
import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserAuthServ implements IUserAuthServ {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final IUserAuthMapper userAuthMapper;

    public UserAuthServ(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder, ITokenService tokenService, AuthenticationManager authenticationManager, IUserAuthMapper userAuthMapper) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userAuthMapper = userAuthMapper;
    }

    @Override
    public String saveUser(User user) {

//        if (userPersistencePort.existsByEmail(user.getEmail())) {
//            // TODO custom exception
//            throw new RuntimeException("UserAuth already exists");
//        }

        UserAuth userAuth = userAuthMapper.toUserAuth(user);

        userAuth.setPassword(passwordEncoder.encode(user.getPassword()));
        userPersistencePort.saveUser(userAuth);

        return tokenService.generateToken(userAuth);
    }

    @Override
    public String authenticateUser(User user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        UserAuth found =(UserAuth)auth.getPrincipal();

//        if (found.isEmpty()) {
//            // TODO custom exception
//            throw new RuntimeException("UserAuth not found");
//        }



        return tokenService.generateToken(found);
    }

//    @Override
//    public void getUser(String email) {
//        userPersistencePort.getUser(email);
//    }
}
