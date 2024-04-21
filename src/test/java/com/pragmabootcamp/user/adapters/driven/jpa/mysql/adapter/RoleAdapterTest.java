package com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragmabootcamp.user.domain.model.Role;
import com.pragmabootcamp.user.testdata.TestConstants;
import com.pragmabootcamp.user.testdata.TestDomainData;
import com.pragmabootcamp.user.testdata.TestSecondaryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoleAdapterTest {

    private RoleAdapter roleAdapter;

    private IRoleRepository roleRepository;
    private IRoleEntityMapper roleEntityMapper;


    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        roleEntityMapper = mock(IRoleEntityMapper.class);
        roleAdapter = new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Test
    void getRole() {
        Role role = TestDomainData.getRole();
        RoleEntity roleEntity = TestSecondaryData.getRoleEntity();
        String roleName = TestConstants.ROLE_NAME;

        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(roleEntity));
        when(roleEntityMapper.toRole(any(RoleEntity.class))).thenReturn(role);

        Role result = roleAdapter.getRole(roleName);

        assertAll(
            () -> assertEquals(role, result),
            () -> verify(roleRepository, times(1)).findByName(anyString()),
            () -> verify(roleEntityMapper, times(1)).toRole(any(RoleEntity.class))
        );
    }
}
