package com.valdir.poolart.services;

import com.valdir.poolart.domain.User;
import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private UserRepository userRepository;

    public void setReview(Integer idUser, Integer review) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isPresent()) {
            user.get().setNumberOfReviews(user.get().getNumberOfReviews() + 1);
            user.get().setSumOfReviews(user.get().getSumOfReviews() + review);
            user.get().setRating(user.get().getSumOfReviews() / user.get().getNumberOfReviews());
            userRepository.save(user.get());
        }
    }
}
