package com.pragmabootcamp.user.adapters.driving.http.rest.mapper;

import com.pragmabootcamp.user.adapters.driving.http.rest.dto.request.AddUserRequest;
import com.pragmabootcamp.user.domain.model.Role;
import com.pragmabootcamp.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roleId", target = "role", qualifiedByName = "mapRole")
    User addUserRequestToUser(AddUserRequest request);

    @Named("mapRole")
    default Role mapRole(int roleId) {
        return new Role(roleId, "To be found");
    }
}
