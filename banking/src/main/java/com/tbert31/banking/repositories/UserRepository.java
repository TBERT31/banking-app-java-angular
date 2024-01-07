package com.tbert31.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tbert31.banking.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
