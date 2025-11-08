package com.mariele.bank.infrastructure.mappers;


import com.mariele.bank.domain.dtos.UserDTO;
import com.mariele.bank.infrastructure.persistences.entities.User;

public class UserMapper {
    public User toEntity(UserDTO userDomainObj) {
        return new User(userDomainObj.username(),
                userDomainObj.email(),
                userDomainObj.password(),
                userDomainObj.account(),
                userDomainObj.balance());
    }

    public UserDTO toDomainObj(User userEntity) {
        return new UserDTO(userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getAccount(),
                userEntity.getBalance());
    }
}
