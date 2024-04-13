package com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragmabootcamp.user.adapters.authentication.UserAuth;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(UserAuth userAuth) {
        userRepository.save(userEntityMapper.toUserEntity(userAuth));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<UserAuth> getUser(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(userEntityMapper::toUser);
    }
}
