package com.valdir.poolart.resources;

import com.valdir.poolart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/number-of-users")
    public ResponseEntity<Integer> findNumberOfUsers() {
        return ResponseEntity.ok().body(service.findNumberOfUsers());
    }

    @GetMapping(value = "/find-all-with-name/{name}")
    public ResponseEntity<Integer> findAllContainingName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.findAllByName(name).size());
    }
}
