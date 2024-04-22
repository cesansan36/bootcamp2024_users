package com.pragmabootcamp.user.adapters.driving.http.rest.mapper;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.LoginUserRequest;
import com.pragmabootcamp.user.domain.model.Role;
import com.pragmabootcamp.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    @Mapping(target = "id", constant = "0L")
    @Mapping(source = "roleId", target = "role", qualifiedByName = "mapRole")
    User addUserRequestToUser(AddUserRequest request, int roleId);

    @Mapping(target = "id", constant = "0L")
    @Mapping(target = "firstName", constant = "transfer")
    @Mapping(target = "lastName", constant = "transfer")
    @Mapping(target = "idDocument", constant = "0L")
    @Mapping(target = "phoneNumber", constant = "0L")
    @Mapping(target = "role", expression = "java(mapRoleLogIn())")
    User loginUserRequestToUser(LoginUserRequest request);

    @Named("mapRole")
    default Role mapRole(int roleId) {
        return new Role(roleId, "To be found");
    }

    default Role mapRoleLogIn() {
        return new Role(0, "To be found");
    }
}
