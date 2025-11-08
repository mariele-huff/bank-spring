package com.mariele.bank.infrastructure.validators;

import com.mariele.bank.domain.dtos.UserDTO;
import com.mariele.bank.infrastructure.exceptions.InvaluableAttributeException;
import com.mariele.bank.infrastructure.exceptions.ResourceNotFoundException;
import com.mariele.bank.infrastructure.mappers.UserMapper;
import com.mariele.bank.infrastructure.persistences.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidator implements Validator<UserDTO> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean canCreate(UserDTO userDTO) {
        if (userDTO.account() <= 0) {
            throw new InvaluableAttributeException("Account number must be greater than zero");
        }

        if ( userRepository.existsByAccount( userDTO.account() ) ) {
            throw new InvaluableAttributeException("Account number already exists");
        }

        if ( userRepository.existsByEmail( userDTO.email() ) ) {
            throw new InvaluableAttributeException("Email already exists");
        }

        if (userDTO.username() == null || userDTO.username().isEmpty()) {
            throw new InvaluableAttributeException("Name cannot be null or empty");
        }

        if (userDTO.email() == null || userDTO.email().isEmpty()) {
            throw new InvaluableAttributeException("Email cannot be null or empty");
        }

        if (isValidEmail(userDTO.email())) {
            throw new InvaluableAttributeException("Email format is invalid");
        }

        return true;
    }

    @Override
    public boolean canUpdate(UserDTO userDTO) {
        if (userDTO.account() <= 0) {
            throw new ResourceNotFoundException("Account number must be greater than zero");
        }

        UserDTO existingUser = userRepository.findByAccount(userDTO.account())
                .map(user -> userMapper.toDomainObj(user))
                .orElseThrow(() -> new ResourceNotFoundException("User not found for account: " + userDTO.account()));

        if (existingUser == null) {
            throw new ResourceNotFoundException("User not found for account: " + userDTO.account());
        }

        if ( isValidEmail(userDTO.email())) {
            throw new InvaluableAttributeException("Email format is invalid");
        }

        return true;
    }

    @Override
    public boolean canDelete(long account) {
        if (account <= 0) {
            throw new ResourceNotFoundException("Account number must be greater than zero");
        }

        UserDTO existingUser = userRepository.findByAccount(account)
                .map(user -> userMapper.toDomainObj(user))
                .orElseThrow(() -> new ResourceNotFoundException("User not found for account: " + account));

        if (existingUser == null) {
            throw new ResourceNotFoundException("User not found for account: " + account);
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email == null || !email.matches(emailRegex);
    }
}
