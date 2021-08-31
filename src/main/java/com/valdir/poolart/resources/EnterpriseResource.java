package com.valdir.poolart.resources;

import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.dto.EnterpriseDTO;
import com.valdir.poolart.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/enterprises")
public class EnterpriseResource {

    @Autowired
    private EnterpriseService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnterpriseDTO> findById(@PathVariable Integer id) {
        Enterprise obj = service.findById(id);
        return ResponseEntity.ok().body(new EnterpriseDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<EnterpriseDTO>> findAll() {
        List<Enterprise> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(EnterpriseDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<EnterpriseDTO> create(@Valid @RequestBody EnterpriseDTO obj) {
        obj = new EnterpriseDTO(service.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EnterpriseDTO> update(@PathVariable Integer id, @Valid @RequestBody EnterpriseDTO obj) {
        obj = new EnterpriseDTO(service.update(id, obj));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EnterpriseDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
