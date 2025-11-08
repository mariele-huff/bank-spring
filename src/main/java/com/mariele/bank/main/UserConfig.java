package com.mariele.bank.main;

import com.marieleprojetos.bank_api_spring.application.gateways.UserGateway;
import com.marieleprojetos.bank_api_spring.application.usecases.CreateUserInteraction;
import com.marieleprojetos.bank_api_spring.infrastructure.mappers.UserMapper;
import com.marieleprojetos.bank_api_spring.infrastructure.persistences.repositories.UserRepository;
import com.marieleprojetos.bank_api_spring.infrastructure.persistences.repositories.gateways.UserRepositoryGateway;
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
        return new UserRepositoryGateway(userRepository, userMapper);
    }

    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }
}
