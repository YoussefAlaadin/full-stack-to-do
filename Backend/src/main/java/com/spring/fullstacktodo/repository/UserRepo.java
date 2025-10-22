package com.spring.fullstacktodo.repository;

import com.spring.fullstacktodo.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
