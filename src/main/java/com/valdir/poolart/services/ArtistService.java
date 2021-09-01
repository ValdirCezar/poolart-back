package com.valdir.poolart.services;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.User;
import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.repositories.ArtistRepository;
import com.valdir.poolart.repositories.UserRepository;
import com.valdir.poolart.services.exceptions.DataIntegrityViolationException;
import com.valdir.poolart.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private static final String ALREADY_REGISTERED = "já cadastrado no sistema!";

    @Autowired
    private ArtistRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Artist findById(Integer id) {
        Optional<Artist> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public Artist create(ArtistDTO obj) {
        obj.setId(null);
        obj.setPassword(encoder.encode(obj.getPassword()));
        validByEmailAndPhone(obj);
        validByCpf(obj);
        return repository.save(new Artist(obj));
    }

    public Artist update(Integer id, ArtistDTO obj) {
        obj.setId(id);
        Artist oldObj = findById(id);

        if(!obj.getPassword().equals(oldObj.getPassword()))
            obj.setPassword(encoder.encode(obj.getPassword()));

        validByEmailAndPhone(obj);
        validByCpf(obj);
        return repository.save(new Artist(obj));
    }

    public void delete(Integer id) {
        repository.delete(findById(id));
    }

    private void validByEmailAndPhone(ArtistDTO dto){
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("E-mail " + ALREADY_REGISTERED);
        }

        user = userRepository.findByPhone(dto.getPhone());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Telefone " + ALREADY_REGISTERED);
        }
    }

    private void validByCpf(ArtistDTO dto){
        Optional<Artist> user = repository.findByCpf(dto.getCpf());
        if(user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("CPF " + ALREADY_REGISTERED);
        }
    }

}
