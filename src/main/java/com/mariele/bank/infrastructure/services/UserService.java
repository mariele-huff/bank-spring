package com.mariele.bank.infrastructure.services;

import com.mariele.bank.infrastructure.persistences.entities.User;
import com.mariele.bank.infrastructure.persistences.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found!"));
    }

    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }
}
