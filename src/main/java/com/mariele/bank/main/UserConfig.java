package com.mariele.bank.main;

import com.mariele.bank.application.gateways.UserGateway;
import com.mariele.bank.application.usecases.CreateUserInteraction;
import com.mariele.bank.infrastructure.mappers.UserMapper;
import com.mariele.bank.infrastructure.persistences.repositories.UserRepository;
import com.mariele.bank.infrastructure.services.UserService;
import com.mariele.bank.infrastructure.validators.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CreateUserInteraction createUserCase(UserGateway userGateway) {
        return new CreateUserInteraction(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserMapper userMapper) {
        return new UserService(userRepository, userMapper);
    }

    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    UserValidator userValidator() {
        return new UserValidator();
    }
}
