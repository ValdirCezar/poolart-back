package com.valdir.poolart.services;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.enums.Profile;
import com.valdir.poolart.repositories.AddressRepository;
import com.valdir.poolart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void startDB() {
        Artist a1 = new Artist(null, "Valdir Cezar", "43984634301", "valdir@mail.com", encoder.encode("123"), "58257957070", "Cantor", 21);
        a1.addProfile(Profile.ADMIN);
        Artist a2 = new Artist(null, "User 2", "43984634302", "email2@mail.com", encoder.encode("123"),  "35543591225", "Cantor", 22);
        Artist a3 = new Artist(null, "User 3", "43984634303", "email3@mail.com", encoder.encode("123"),  "32434616500", "Cantor", 23);
        Artist a4 = new Artist(null, "User 4", "43984634304", "email4@mail.com", encoder.encode("123"),  "43262842346", "Cantor", 24);
        Artist a5 = new Artist(null, "User 5", "43984634305", "email5@mail.com", encoder.encode("123"),  "82737411092", "Cantor", 25);
        Artist a6 = new Artist(null, "User 6", "43984634306", "email6@mail.com", encoder.encode("123"),  "16186255867", "Cantor", 26);
        Artist a7 = new Artist(null, "User 7", "43984634307", "email7@mail.com", encoder.encode("123"),  "51475141718", "Cantor", 27);

        Enterprise e1 = new Enterprise(null, "Enterprise 1", "43991518431", "email10@mail.com", encoder.encode("123"),  "Description od enterprise 1", "07360730000142");
        Enterprise e2 = new Enterprise(null, "Enterprise 2", "43991518432", "email20@mail.com", encoder.encode("123"),  "Description od enterprise 2", "52218182000175");
        Enterprise e3 = new Enterprise(null, "Enterprise 3", "43991518433", "email30@mail.com", encoder.encode("123"),  "Description od enterprise 3", "64743709000160");
        Enterprise e4 = new Enterprise(null, "Enterprise 4", "43991518434", "email40@mail.com", encoder.encode("123"),  "Description od enterprise 4", "97695097000136");
        Enterprise e5 = new Enterprise(null, "Enterprise 5", "43991518435", "email50@mail.com", encoder.encode("123"),  "Description od enterprise 5", "04373868000198");
        Enterprise e6 = new Enterprise(null, "Enterprise 6", "43991518436", "email60@mail.com", encoder.encode("123"),  "Description od enterprise 6", "70696202000103");
        Enterprise e7 = new Enterprise(null, "Enterprise 7", "43991518437", "email70@mail.com", encoder.encode("123"),  "Description od enterprise 7", "27119176000113");

        Address ad1  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a1);
        Address ad2  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a2);
        Address ad3  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a3);
        Address ad4  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a4);
        Address ad5  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a5);
        Address ad6  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a6);
        Address ad7  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", a7);
        Address ad8  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e1);
        Address ad9  = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e2);
        Address ad10 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e3);
        Address ad11 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e4);
        Address ad12 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e5);
        Address ad13 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e6);
        Address ad14 = new Address(null, "86086086", "Brasil", "Londrina", "Rua dos teste", "999", e7);

        userRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, e1, e2, e3, e4, e5, e6, e7));
        addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4, ad5, ad6, ad7, ad8, ad9, ad10, ad11, ad12, ad13, ad14));
    }
}
