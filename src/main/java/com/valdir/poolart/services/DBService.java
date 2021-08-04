package com.valdir.poolart.services;

import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    public void startDB() {

    }
}
