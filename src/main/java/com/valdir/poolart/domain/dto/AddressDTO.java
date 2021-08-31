package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AddressDTO {

    private Integer id;
    private String cep;
    private String country;
    private String city;
    private String street;
    private String number;
    private Integer user;

    public AddressDTO(Address model) {
        this.id = model.getId();
        this.cep = model.getCep();
        this.country = model.getCountry();
        this.city = model.getCity();
        this.street = model.getStreet();
        this.number = model.getNumber();
        this.user = model.getUser().getId();
    }
}
