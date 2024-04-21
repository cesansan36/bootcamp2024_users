package com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter;

import com.pragmabootcamp.user.adapters.driven.authentication.UserAuth;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragmabootcamp.user.testdata.TestConstants;
import com.pragmabootcamp.user.testdata.TestSecondaryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserAdapterTest {

    private UserAdapter userAdapter;

    private IUserRepository userRepository;
    private IUserEntityMapper userEntityMapper;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        userEntityMapper = mock(IUserEntityMapper.class);
        userAdapter = new UserAdapter(userRepository, userEntityMapper);
    }

    @Test
    void saveUser() {
        UserAuth userAuth = TestSecondaryData.getUserAuth();
        UserEntity userEntity = TestSecondaryData.getUserEntity();

        when(userEntityMapper.toUserEntity(any(UserAuth.class))).thenReturn(userEntity);

        userAdapter.saveUser(userAuth);

        assertAll(
            () -> verify(userRepository, times(1)).save(any(UserEntity.class)),
            () -> verify(userEntityMapper, times(1)).toUserEntity(any(UserAuth.class))
        );
    }

    @Test
    void existsByEmail() {
        UserEntity userEntity = TestSecondaryData.getUserEntity();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        boolean result = userAdapter.existsByEmail(TestConstants.ROLE_NAME);

        assertTrue(result);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void existsByEmailNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        boolean result = userAdapter.existsByEmail(TestConstants.EMAIL);

        assertFalse(result);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void getUser() {
        String email = TestConstants.EMAIL;
        UserEntity userEntity = TestSecondaryData.getUserEntity();
        UserAuth userAuth = TestSecondaryData.getUserAuth();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUser(any(UserEntity.class))).thenReturn(userAuth);

        Optional<UserAuth> result = userAdapter.getUser(email);

        assertAll(
            () -> assertTrue(result.isPresent()),
            () -> verify(userRepository, times(1)).findByEmail(anyString()),
            () -> verify(userEntityMapper, times(1)).toUser(any(UserEntity.class)),
            () -> assertEquals(userAuth.getEmail(), email)
        );
    }
}
