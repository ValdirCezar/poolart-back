package com.valdir.poolart.services;

import com.amazonaws.services.licensemanager.model.AuthorizationException;
import com.valdir.poolart.domain.User;
import com.valdir.poolart.repositories.UserRepository;
import com.valdir.poolart.services.exceptions.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

@Log4j2
@Service
public class UserService {

    @Autowired
    private S3Service s3service;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository repository;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Integer findNumberOfUsers() {
        return repository.findAll().size();
    }

    public List<User> findAllByName(String name) {
        return repository.findByNameContaining(name);
    }

    public User authenticated() {
        try {
            String context = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            return repository.findByEmail(context).orElseThrow(() -> new AuthorizationException("Acesso negado"));
        } catch (Exception e) {
            return null;
        }
    }

    public URI uploadProfilePicture(MultipartFile multiPartFile) {
        log.info("USER_SERVICE ::: Entrou no uploadProfilePicture");

        User user = authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String fileName = prefix + user.getId() + ".jpg";

        log.info("USER_SERVICE ::: Saindo do uploadProfilePicture");
        return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

    }

    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado")
        );

    }
}
