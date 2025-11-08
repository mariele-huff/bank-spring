package com.mariele.bank.application.usecases;

import com.mariele.bank.application.gateways.UserGateway;
import com.mariele.bank.domain.dtos.OperationalDTO;
import com.mariele.bank.domain.dtos.TransferDTO;
import com.mariele.bank.domain.dtos.UserDTO;

import java.math.BigDecimal;
import java.util.List;

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

    public List<UserDTO> getAllUsers() {
        return userGateway.getAllUsers();
    }

    public UserDTO updateUser(UserDTO user) {
        return userGateway.updateUser(user);
    }

    public void deleteUser(long account) {
        userGateway.deleteUser(account);
    }

    public void transferFunds(TransferDTO transferDTO) {
        userGateway.transferFunds(transferDTO);
    }

    public void depositFunds(OperationalDTO operationalDTO) {
        userGateway.depositFunds(operationalDTO);
    }

    public void withdrawFunds(OperationalDTO operationalDTO) {
        userGateway.withdrawFunds(operationalDTO);
    }

    public BigDecimal getBalance(long account) {
        return userGateway.getBalance(account);
    }
}
