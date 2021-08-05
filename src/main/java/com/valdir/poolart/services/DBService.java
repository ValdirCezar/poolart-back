package com.valdir.poolart.services;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.enums.Profile;
import com.valdir.poolart.repositories.AddressRepository;
import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void startDB() {
        Artist a1 = new Artist(null, "Valdir Cezar", "43984634308", "valdr@mail.com", "123", Profile.ADMIN, "582.579.570-70", "Cantor", 21);
        a1.addSkill("Sabe programar cantando kkk");

        Enterprise e1 = new Enterprise(null, "ZACK BURGUER", "43991518434", "zack@mail.com", "123", Profile.ENTERPRISE, "Amburgueria do Matheus", "07360730000142");

        Address address1 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a1);
        Address address2 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e1);

        userRepository.saveAll(Arrays.asList(a1, e1));
        addressRepository.saveAll(Arrays.asList(address1, address2));
    }
}
