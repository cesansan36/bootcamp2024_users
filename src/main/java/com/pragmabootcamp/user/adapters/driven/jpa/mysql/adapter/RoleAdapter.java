package com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragmabootcamp.user.domain.model.Role;
import com.pragmabootcamp.user.domain.secondaryport.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Role getRole(String name) {
        RoleEntity roleEntity = roleRepository.findByName(name).orElseThrow();
        return roleEntityMapper.toRole(roleEntity);
    }
}
