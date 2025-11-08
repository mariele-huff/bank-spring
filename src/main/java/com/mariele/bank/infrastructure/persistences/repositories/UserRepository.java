package com.mariele.bank.infrastructure.persistences.repositories;

import com.mariele.bank.infrastructure.persistences.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByAccount(long account);

    boolean existsByAccount(long account);

    boolean existsByEmail(String email);

    default Optional<User> update(User user) {
        User updatedUser = findByAccount(user.getAccount()).orElse(null);
        if (updatedUser != null) {
            updatedUser.setAccount(updatedUser.getAccount());
            updatedUser.setUsername(user.getUsername() != null ? user.getUsername() : updatedUser.getUsername());
            updatedUser.setEmail(user.getEmail() != null ? user.getEmail() : updatedUser.getEmail());
            updatedUser.setPassword(user.getPassword() != null ? user.getPassword() : updatedUser.getPassword());
            updatedUser.setBalance(user.getBalance() != null ? user.getBalance() : updatedUser.getBalance());
            saveAndFlush(updatedUser);
        }
        return Optional.ofNullable(updatedUser);
    }

    @Transactional
    void deleteByAccount(long account);
}
