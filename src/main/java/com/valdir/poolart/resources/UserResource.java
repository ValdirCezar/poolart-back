package com.valdir.poolart.resources;

import com.valdir.poolart.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Log4j2
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

    @PostMapping(value = "/image/upload")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        log.info("USER_SERVICE ::: Entrou no uploadProfilePicture");
        URI uri = service.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }
}
