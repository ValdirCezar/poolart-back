package com.valdir.poolart.services;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.Offer;
import com.valdir.poolart.domain.enums.Profile;
import com.valdir.poolart.domain.enums.Status;
import com.valdir.poolart.repositories.OfferRepository;
import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    public void startDB() {
        Artist a1 = new Artist(null, "Valdir Cezar", "43984634308", "valdr@mail.com", "123", Profile.ADMIN, "582.579.570-70", "Cantor", 21);
        a1.addSkill("Sabe programar cantando kkk");

        Enterprise e1 = new Enterprise(null, "ZACK BURGUER", "43991518434", "zack@mail.com", "123", Profile.ENTERPRISE, "Amburgueria do Matheus", "07360730000142");

        Offer o1 = new Offer(null, "Offerta 1", LocalDate.now(), 125.5F, Status.OPEN, "Observações 01", e1);
        Offer o2 = new Offer(null, "Offerta 2", LocalDate.now(), 125.5F, Status.IN_PROGRESS, "Observações 02", e1);
        Offer o3 = new Offer(null, "Offerta 3", LocalDate.now(), 125.5F, Status.CANCELED, "Observações 03", a1);
        Offer o4 = new Offer(null, "Offerta 4", LocalDate.now(), 125.5F, Status.CLOSED, "Observações 04", a1);

        userRepository.saveAll(Arrays.asList(a1, e1));
        offerRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
    }
}
