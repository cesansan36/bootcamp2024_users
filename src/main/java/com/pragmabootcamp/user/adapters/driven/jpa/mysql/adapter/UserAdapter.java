package com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getUser(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(userEntityMapper::toUser);
    }
}
