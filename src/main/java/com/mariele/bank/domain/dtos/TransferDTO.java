package com.mariele.bank.domain.dtos;

public record TransferDTO( long fromAccount, long toAccount, double amount) {
}
