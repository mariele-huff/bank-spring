package com.mariele.bank.infrastructure.services;

import com.mariele.bank.application.gateways.UserGateway;
import com.mariele.bank.domain.dtos.UserDTO;
import com.mariele.bank.infrastructure.exceptions.ResourceNotFoundException;
import com.mariele.bank.infrastructure.mappers.UserMapper;
import com.mariele.bank.infrastructure.persistences.entities.User;
import com.mariele.bank.infrastructure.persistences.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserGateway {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        User entity = userMapper.toEntity(user);
        entity = repository.save(entity);
        return userMapper.toDomainObj(entity);
    }

    @Override
    public UserDTO getUserByAccount(long account) {
        User entity = repository.findByAccount(account).orElseThrow(() -> new ResourceNotFoundException("Account not found!"));

        return userMapper.toDomainObj(entity);
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User entity = userMapper.toEntity(user);
        entity = repository.update(entity).orElseThrow( () -> new ResourceNotFoundException("User not found!") );
        return userMapper.toDomainObj(entity);
    }

    @Override
    public void deleteUser(long account) {
        repository.deleteByAccount(account);
    }
}
