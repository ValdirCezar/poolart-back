package com.valdir.poolart.services;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.repositories.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Artist findById(Integer id) {
        Optional<Artist> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public Artist create(ArtistDTO obj) {
        obj.setId(null);
        return repository.save(mapper.map(obj, Artist.class));
    }
}
