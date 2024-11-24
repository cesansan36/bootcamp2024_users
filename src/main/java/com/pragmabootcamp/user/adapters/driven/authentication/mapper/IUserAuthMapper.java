package com.pragmabootcamp.user.adapters.driven.authentication.mapper;

import com.pragmabootcamp.user.adapters.driven.authentication.UserAuth;
import com.pragmabootcamp.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserAuthMapper {

    User toUser(UserAuth userAuth);

    UserAuth toUserAuth(User user);
}
