package com.pragmabootcamp.user.adapters.authentication;

import com.pragmabootcamp.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserAuthMapper {

    User toUser(UserAuth userAuth);

    UserAuth toUserAuth(User user);
}
