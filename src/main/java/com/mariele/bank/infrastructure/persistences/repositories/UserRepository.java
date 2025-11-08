package com.mariele.bank.infrastructure.persistences.repositories;

import com.mariele.bank.infrastructure.persistences.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email );

    @Transactional
    void deleteByEmail(String email);
}
