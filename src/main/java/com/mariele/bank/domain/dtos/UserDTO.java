package com.mariele.bank.domain.dtos;

import java.math.BigDecimal;

public record UserDTO(String username, String email, String password, long account, BigDecimal balance) {
}
