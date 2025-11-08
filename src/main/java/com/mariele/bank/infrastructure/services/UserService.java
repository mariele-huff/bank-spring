package com.mariele.bank.infrastructure.services;

import com.mariele.bank.application.gateways.UserGateway;
import com.mariele.bank.domain.dtos.OperationalDTO;
import com.mariele.bank.domain.dtos.TransferDTO;
import com.mariele.bank.domain.dtos.UserDTO;
import com.mariele.bank.infrastructure.exceptions.ResourceNotFoundException;
import com.mariele.bank.infrastructure.mappers.UserMapper;
import com.mariele.bank.infrastructure.persistences.entities.User;
import com.mariele.bank.infrastructure.persistences.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
    public List<UserDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(userMapper::toDomainObj)
                .toList();
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

    @Override
    public void transferFunds( TransferDTO transferDTO) {
        User fromUser = repository.findByAccount(transferDTO.fromAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Source account not found!"));

        User toUser = repository.findByAccount(transferDTO.toAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Destination account not found!"));

        boolean hasSufficientFunds = fromUser.getBalance().compareTo(BigDecimal.valueOf(transferDTO.amount())) >= 0;

        if (!hasSufficientFunds) {
            throw new RuntimeException("Insufficient funds in source account!");
        }

        else
        {
            fromUser.setBalance( fromUser.getBalance().subtract( BigDecimal.valueOf(transferDTO.amount()) ) );
            toUser.setBalance( toUser.getBalance().add( BigDecimal.valueOf(transferDTO.amount()) ) );

            repository.update( fromUser );
            repository.update( toUser );
        }
    }

    @Override
    public void depositFunds(OperationalDTO operationalDTO) {
        User toUser = repository.findByAccount(operationalDTO.account())
                .orElseThrow(() -> new ResourceNotFoundException("Destination account not found!"));

        toUser.setBalance( toUser.getBalance().add( BigDecimal.valueOf(operationalDTO.amount()) ) );
        repository.update( toUser );
    }

    @Override
    public void withdrawFunds(OperationalDTO operationalDTO) {
        User fromUser = repository.findByAccount(operationalDTO.account())
                .orElseThrow(() -> new ResourceNotFoundException("Source account not found!"));

        boolean hasSufficientFunds = fromUser.getBalance().compareTo(BigDecimal.valueOf(operationalDTO.amount())) >= 0;

        if (!hasSufficientFunds) {
            throw new RuntimeException("Insufficient funds in source account!");
        }

        else
        {
            fromUser.setBalance( fromUser.getBalance().subtract( BigDecimal.valueOf(operationalDTO.amount()) ) );

            repository.update( fromUser );
        }
    }

    @Override
    public BigDecimal getBalance(long account) {
        User user = repository.findByAccount(account)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found!"));

        return user.getBalance();
    }
}
