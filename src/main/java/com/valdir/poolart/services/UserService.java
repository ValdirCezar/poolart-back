package com.valdir.poolart.services;

import com.valdir.poolart.domain.User;
import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Integer findNumberOfUsers() {
        return repository.findAll().size();
    }

    public List<User> findAllByName(String name) {
        return repository.findByNameContaining(name);
    }
}
