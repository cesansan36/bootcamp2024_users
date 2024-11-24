package com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragmabootcamp.user.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {

    public RoleEntity toRoleEntity(Role role);
    public Role toRole(RoleEntity roleEntity);
}
