package com.valdir.poolart.services;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.User;
import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.repositories.ArtistRepository;
import com.valdir.poolart.repositories.UserRepository;
import com.valdir.poolart.services.exceptions.DataIntegrityViolationException;
import com.valdir.poolart.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private static final String ALREADY_REGISTERED = "já cadastrado no sistema!";

    @Autowired
    private ArtistRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public Artist findById(Integer id) {
        Optional<Artist> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public Artist create(ArtistDTO obj) {
        obj.setId(null);
        validByEmailAndPhone(obj);
        validByCpf(obj);
        return repository.save(mapper.map(obj, Artist.class));
    }

    private void validByEmailAndPhone(ArtistDTO dto){
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if(user.isPresent()) {
            throw new DataIntegrityViolationException("E-mail " + ALREADY_REGISTERED);
        }

        user = userRepository.findByPhone(dto.getPhone());
        if(user.isPresent()) {
            throw new DataIntegrityViolationException("Telefone " + ALREADY_REGISTERED);
        }
    }

    private void validByCpf(ArtistDTO dto){
        Optional<Artist> user = repository.findByCpf(dto.getCpf());
        if(user.isPresent()) {
            throw new DataIntegrityViolationException("CPF " + ALREADY_REGISTERED);
        }
    }
}
