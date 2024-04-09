package com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragmabootcamp.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    public UserEntity toUserEntity(User user);
    public User toUser(UserEntity userEntity);

}
