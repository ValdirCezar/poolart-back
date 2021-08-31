package com.valdir.poolart.services;

import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.User;
import com.valdir.poolart.domain.dto.EnterpriseDTO;
import com.valdir.poolart.repositories.EnterpriseRepository;
import com.valdir.poolart.repositories.UserRepository;
import com.valdir.poolart.services.exceptions.DataIntegrityViolationException;
import com.valdir.poolart.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService {

    private static final String ALREADY_REGISTERED = "já cadastrado no sistema!";

    @Autowired
    private EnterpriseRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Enterprise findById(Integer id) {
        Optional<Enterprise> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Enterprise> findAll() {
        return repository.findAll();
    }

    public Enterprise create(EnterpriseDTO obj) {
        obj.setId(null);
        validByEmailAndPhone(obj);
        validByCnpj(obj);
        return repository.save(new Enterprise(obj));
    }

    public Enterprise update(Integer id, EnterpriseDTO obj) {
        obj.setId(id);
        findById(id);
        validByEmailAndPhone(obj);
        validByCnpj(obj);
        return repository.save(new Enterprise(obj));
    }

    public void delete(Integer id) {
        repository.delete(findById(id));
    }

    private void validByEmailAndPhone(EnterpriseDTO dto){
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("E-mail " + ALREADY_REGISTERED);
        }

        user = userRepository.findByPhone(dto.getPhone());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Telefone " + ALREADY_REGISTERED);
        }
    }

    private void validByCnpj(EnterpriseDTO dto){
        Optional<Enterprise> user = repository.findByCnpj(dto.getCnpj());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("CNPJ " + ALREADY_REGISTERED);
        }
    }

}
