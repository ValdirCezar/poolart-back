package com.valdir.poolart.services;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    public void startDB() {
        Artist a1 = new Artist(null, "Valdir Cezar", PersonType.PHYSICAL, "43984634308", "valdr@mail.com", "123", Profile.ADMIN, "582.579.570-70", "Cantor", 21);
        a1.addSkill("Sabe programar cantando kkk");

        Enterprise e1 = new Enterprise(null, "ZACK BURGUER", PersonType.LEGAL, "43991518434", "zack@mail.com", "123", Profile.ENTERPRISE, "Amburgueria do Matheus", "07360730000142");

        userRepository.saveAll(Arrays.asList(a1, e1));
    }
}
