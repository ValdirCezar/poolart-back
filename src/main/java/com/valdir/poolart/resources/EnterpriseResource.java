package com.valdir.poolart.resources;

import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.dto.EnterpriseDTO;
import com.valdir.poolart.services.EnterpriseService;
import org.modelmapper.ModelMapper;
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

    private static final String ENDPOINT_ID = "/{id}";

    @Autowired
    private EnterpriseService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ENDPOINT_ID)
    public ResponseEntity<EnterpriseDTO> findById(@PathVariable Integer id) {
        Enterprise obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.map(obj, EnterpriseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<EnterpriseDTO>> findAll() {
        List<Enterprise> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> mapper.map(obj, EnterpriseDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<EnterpriseDTO> create(@Valid @RequestBody EnterpriseDTO obj) {
        obj = mapper.map(service.create(obj), EnterpriseDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ENDPOINT_ID).buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ENDPOINT_ID)
    public ResponseEntity<EnterpriseDTO> update(@PathVariable Integer id, @Valid @RequestBody EnterpriseDTO obj) {
        obj = mapper.map(service.update(id, obj), EnterpriseDTO.class);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = ENDPOINT_ID)
    public ResponseEntity<EnterpriseDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
