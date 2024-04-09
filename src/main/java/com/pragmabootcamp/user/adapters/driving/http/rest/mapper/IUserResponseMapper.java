package com.pragmabootcamp.user.adapters.driving.http.rest.mapper;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;
import com.pragmabootcamp.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {

    @Mapping(source = "role.name", target = "role")
    UserResponse toUserResponse(User user);
}
