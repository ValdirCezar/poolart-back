package com.valdir.poolart.resources;

import com.valdir.poolart.services.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

    @Autowired
    private ReviewService service;

    @GetMapping(value = "/{idUser}/{rating}")
    public ResponseEntity<Void> setReview(@PathVariable Integer idUser, @PathVariable Integer rating) {
        log.info("REVIEW RESOURCE ::: Entrou no setReview");
        service.setReview(idUser, rating);
        return ResponseEntity.ok().build();
    }
}
