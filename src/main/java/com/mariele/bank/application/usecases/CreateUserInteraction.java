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
}
