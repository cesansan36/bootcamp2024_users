package com.pragmabootcamp.user.domain.primaryport.usecase;

import com.pragmabootcamp.user.domain.exception.UserAlreadyExistsException;
import com.pragmabootcamp.user.domain.model.Token;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.secondaryport.IUserAuthServ;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import com.pragmabootcamp.user.testdata.TestConstants;
import com.pragmabootcamp.user.testdata.TestDomainData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserUseCaseTest {

    private UserUseCase userUseCase;

    private IUserAuthServ userAuthServ;
    private IUserPersistencePort userPersistencePort;

    @BeforeEach
    void setUp() {
        userAuthServ = mock(IUserAuthServ.class);
        userPersistencePort = mock(IUserPersistencePort.class);
        userUseCase = new UserUseCase(userAuthServ, userPersistencePort);
    }

    @Test
    void saveUser() {
        User user = TestDomainData.getUser();
        String tokenString = TestConstants.TOKEN;

        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userAuthServ.saveUser(any(User.class))).thenReturn(tokenString);

        Token token = userUseCase.saveUser(user);

        assertAll(
            () -> assertEquals(tokenString, token.getValue()),
            () -> verify(userAuthServ, times(1)).saveUser(any(User.class)),
            () -> verify(userPersistencePort, times(1)).existsByEmail(anyString())
        );
    }

    @Test
    void saveUserException() {
        User user = TestDomainData.getUser();
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void authenticateUser() {
        User user = TestDomainData.getUser();
        String tokenString = TestConstants.TOKEN;

        when(userAuthServ.authenticateUser(any(User.class))).thenReturn(tokenString);

        Token token = userUseCase.authenticateUser(user);

        assertAll(
            () -> assertEquals(tokenString, token.getValue()),
            () -> verify(userAuthServ, times(1)).authenticateUser(any(User.class))
        );
    }
}
