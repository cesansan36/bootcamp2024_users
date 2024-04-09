package com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragmabootcamp.user.domain.model.User;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            // TODO custom exception
            throw new RuntimeException("User already exists");
        }

        userRepository.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    public User getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();
        return userEntityMapper.toUser(userEntity);
    }
}
