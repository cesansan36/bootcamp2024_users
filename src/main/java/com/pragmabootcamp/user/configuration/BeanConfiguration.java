package com.pragmabootcamp.user.configuration;

import com.pragmabootcamp.user.adapters.driven.authentication.mapper.IUserAuthMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragmabootcamp.user.adapters.driven.authentication.ITokenService;
import com.pragmabootcamp.user.domain.secondaryport.IUserAuthServ;
import com.pragmabootcamp.user.adapters.driven.authentication.adapter.UserAuthServ;
import com.pragmabootcamp.user.domain.primaryport.IUserPrimaryPort;
import com.pragmabootcamp.user.domain.primaryport.usecase.UserUseCase;
import com.pragmabootcamp.user.domain.secondaryport.IRolePersistencePort;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserAuthMapper userAuthMapper;
    private final ITokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserAuthServ userAuthServ() {
        return new UserAuthServ(userPersistencePort(), passwordEncoder, tokenService, authenticationManager, userAuthMapper);
    }

    @Bean
    public IUserPrimaryPort userPrimaryPort(){
        return new UserUseCase( userAuthServ(), userPersistencePort());
    }
}
