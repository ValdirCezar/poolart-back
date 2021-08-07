package com.valdir.poolart.services;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.dto.AddressDTO;
import com.valdir.poolart.repositories.AddressRepository;
import com.valdir.poolart.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private ModelMapper mapper;


    public Address findById(Integer id) {
        Optional<Address> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address create(AddressDTO obj) {
        obj.setId(null);
        return repository.save(mapper.map(obj, Address.class));
    }

    public Address update(Integer id, AddressDTO obj) {
        obj.setId(id);
        findById(id);
        return repository.save(mapper.map(obj, Address.class));
    }

}
