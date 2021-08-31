package com.valdir.poolart.resources;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/artists")
public class ArtistResource {

    @Autowired
    private ArtistService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtistDTO> findById(@PathVariable Integer id) {
        Artist obj = service.findById(id);
        return ResponseEntity.ok().body(new ArtistDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> findAll() {
        List<Artist> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(ArtistDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> create(@Valid @RequestBody ArtistDTO obj) {
        obj = new ArtistDTO(service.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArtistDTO> update(@PathVariable Integer id, @Valid @RequestBody ArtistDTO obj) {
        obj = new ArtistDTO(service.update(id, obj));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ArtistDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
