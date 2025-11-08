package com.mariele.bank.application.gateways;

import com.mariele.bank.domain.dtos.OperationalDTO;
import com.mariele.bank.domain.dtos.TransferDTO;
import com.mariele.bank.domain.dtos.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public interface UserGateway {
    UserDTO createUser(UserDTO user);
    UserDTO getUserByAccount(long account);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO user);
    void deleteUser(long account);
    void transferFunds(TransferDTO transferDTO);
    void depositFunds(OperationalDTO operationalDTO);
    void withdrawFunds(OperationalDTO operationalDTO);
    BigDecimal getBalance(long account);
}
