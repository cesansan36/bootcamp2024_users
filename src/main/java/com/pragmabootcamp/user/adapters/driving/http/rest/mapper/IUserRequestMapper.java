package com.pragmabootcamp.user.adapters.driving.http.rest.mapper;

import com.pragmabootcamp.user.adapters.authentication.UserAuth;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.LoginUserRequest;
import com.pragmabootcamp.user.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roleId", target = "role", qualifiedByName = "mapRole")
    UserAuth addUserRequestToUser(AddUserRequest request, int roleId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", constant = "transfer")
    @Mapping(target = "lastName", constant = "transfer")
    @Mapping(target = "idDocument", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserAuth loginUserRequestToUser(LoginUserRequest request);

    @Named("mapRole")
    default Role mapRole(int roleId) {
        return new Role(roleId, "To be found");
    }
}
