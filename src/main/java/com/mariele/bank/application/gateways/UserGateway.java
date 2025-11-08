package com.mariele.bank.application.gateways;

import com.mariele.bank.domain.dtos.UserDTO;

public interface UserGateway {
    UserDTO createUser(UserDTO user);
    UserDTO getUserByAccount(long account);
    UserDTO updateUser(UserDTO user);
    void deleteUser(long account);
}
