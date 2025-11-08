package com.mariele.bank.application.usecases;

import com.mariele.bank.application.gateways.UserGateway;
import com.mariele.bank.domain.dtos.UserDTO;

public class CreateUserInteraction {
    private final UserGateway userGateway;

    public CreateUserInteraction(UserGateway createUser) {
        this.userGateway = createUser;
    }

    public UserDTO createUser(UserDTO user) {
        return userGateway.createUser(user);
    }

    public UserDTO getUser(long account) {
        return userGateway.getUserByAccount(account);
    }

    public UserDTO updateUser(UserDTO user) {
        return userGateway.updateUser(user);
    }

    public void deleteUser(long account) {
        userGateway.deleteUser(account);
    }
}
