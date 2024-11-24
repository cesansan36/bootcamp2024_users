package com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper;

import com.pragmabootcamp.user.adapters.driven.authentication.UserAuth;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    public UserEntity toUserEntity(UserAuth userAuth);
    public UserAuth toUser(UserEntity userEntity);

}
