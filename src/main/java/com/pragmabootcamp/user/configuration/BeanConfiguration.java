package com.pragmabootcamp.user.configuration;

import com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragmabootcamp.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragmabootcamp.user.domain.authentication.ITokenService;
import com.pragmabootcamp.user.domain.primaryport.IUserServicePort;
import com.pragmabootcamp.user.domain.primaryport.usecase.UserUseCase;
import com.pragmabootcamp.user.domain.secondaryport.IRolePersistencePort;
import com.pragmabootcamp.user.domain.secondaryport.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final ITokenService tokenService;
    private final AuthenticationConfiguration config;


    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() throws Exception {
        return new UserUseCase(userPersistencePort(), passwordEncoder(), tokenService, authenticationManager());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userEntityMapper.toUser(userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found")));
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return config.getAuthenticationManager();
    }
}
