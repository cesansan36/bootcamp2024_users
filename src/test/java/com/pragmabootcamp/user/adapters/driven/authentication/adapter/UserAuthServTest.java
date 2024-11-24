package com.pragmabootcamp.user.adapters.driven.authentication.adapter;

import com.pragmabootcamp.user.adapters.driven.authentication.ITokenService;
import com.pragmabootcamp.user.adapters.driven.authentication.UserAuth;
import com.pragmabootcamp.user.adapters.driven.authentication.mapper.IUserAuthMapper;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import com.pragmabootcamp.user.testdata.TestConstants;
import com.pragmabootcamp.user.testdata.TestDomainData;
import com.pragmabootcamp.user.testdata.TestSecondaryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserAuthServTest {

    private UserAuthServ userAuthServ;

    private IUserPersistencePort userPersistencePort;
    private PasswordEncoder passwordEncoder;
    private ITokenService tokenService;
    private AuthenticationManager authenticationManager;
    private IUserAuthMapper userAuthMapper;

    @BeforeEach
    void setUp() {
        userPersistencePort = mock(IUserPersistencePort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        tokenService = mock(ITokenService.class);
        authenticationManager = mock(AuthenticationManager.class);
        userAuthMapper = mock(IUserAuthMapper.class);
        userAuthServ = new UserAuthServ(userPersistencePort, passwordEncoder, tokenService, authenticationManager, userAuthMapper);
    }

    @Test
    void saveUser() {
        User user = TestDomainData.getUser();
        UserAuth userAuth = TestSecondaryData.getUserAuth();
        String password = TestConstants.PASSWORD;
        String token = TestConstants.TOKEN;

        when(userAuthMapper.toUserAuth(user)).thenReturn(userAuth);
        when(passwordEncoder.encode(anyString())).thenReturn(password);
        when(tokenService.generateToken(any(UserAuth.class))).thenReturn(token);

        String result = userAuthServ.saveUser(user);

        assertAll(
            () -> assertEquals(token, result),
            () -> verify(userAuthMapper, times(1)).toUserAuth(any(User.class)),
            () -> verify(passwordEncoder, times(1)).encode(anyString()),
            () -> verify(tokenService, times(1)).generateToken(any(UserAuth.class))
        );
    }

    @Test
    void authenticateUser() {
        Authentication auth = mock(Authentication.class);
        User user = TestDomainData.getUser();
        UserAuth userAuth = TestSecondaryData.getUserAuth();
        String token = TestConstants.TOKEN;

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userAuth);
        when(tokenService.generateToken(any(UserAuth.class))).thenReturn(token);

        String result = userAuthServ.authenticateUser(user);

        assertAll(
            () -> assertEquals(token, result),
            () -> verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class)),
            () -> verify(tokenService, times(1)).generateToken(any(UserAuth.class))
        );
    }
}
