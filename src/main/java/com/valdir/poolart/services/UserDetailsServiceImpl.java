package com.valdir.poolart.services;

import com.valdir.poolart.domain.User;
import com.valdir.poolart.repositories.UserRepository;
import com.valdir.poolart.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Interface central que carrega dados específicos do usuário
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // Localiza o usuário com base no nome de usuário
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getProfiles());
    }
}