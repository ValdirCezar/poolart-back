package com.valdir.poolart.resources;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.services.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/artists")
public class ArtistResource {

    @Autowired
    private ArtistService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtistDTO> findById(@PathVariable Integer id) {
        Artist obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.map(obj, ArtistDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> findById() {
        List<Artist> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> mapper.map(obj, ArtistDTO.class)).collect(Collectors.toList()));
    }
}
