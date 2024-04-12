package com.pragmabootcamp.user.adapters.driving.http.rest.mapper;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.response.UserResponse;
import com.pragmabootcamp.user.domain.model.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {

    @Mapping(source = "value", target = "token")
    UserResponse toUserResponse(Token token);
}
