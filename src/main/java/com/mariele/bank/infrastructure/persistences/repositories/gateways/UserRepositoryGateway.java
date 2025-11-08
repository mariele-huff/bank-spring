package com.mariele.bank.infrastructure.persistences.repositories.gateways;

import com.marieleprojetos.bank_api_spring.application.gateways.UserGateway;
import com.marieleprojetos.bank_api_spring.domain.dtos.UserDTO;
import com.marieleprojetos.bank_api_spring.infrastructure.mappers.UserMapper;
import com.marieleprojetos.bank_api_spring.infrastructure.persistences.entities.User;
import com.marieleprojetos.bank_api_spring.infrastructure.persistences.repositories.UserRepository;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDomainObj) {
        User userEntity = userMapper.toEntity(userDomainObj);
        User savedEntity = userRepository.save(userEntity);

        return userMapper.toDomainObj(savedEntity);
    }
}
