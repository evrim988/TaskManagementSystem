package org.example.taskmanagementsystemproject.repository;

import org.example.taskmanagementsystemproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByUsername(String username);

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);
}
