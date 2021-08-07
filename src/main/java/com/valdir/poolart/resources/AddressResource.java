package com.valdir.poolart.resources;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.dto.AddressDTO;
import com.valdir.poolart.services.AddressService;
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
@RequestMapping(value = "/address")
public class AddressResource {

    @Autowired
    private AddressService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Integer id) {
        Address obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.map(obj, AddressDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        List<Address> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> mapper.map(obj, AddressDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO obj) {
        obj = mapper.map(service.create(obj), AddressDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Integer id, @Valid @RequestBody AddressDTO obj) {
        obj = mapper.map(service.update(id, obj), AddressDTO.class);
        return ResponseEntity.ok().body(obj);
    }

}
