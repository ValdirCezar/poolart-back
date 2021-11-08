package com.valdir.poolart.repositories;

import com.valdir.poolart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    List<User> findByNameContaining(String name);
}
