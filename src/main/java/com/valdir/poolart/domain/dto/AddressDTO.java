package com.valdir.poolart.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.poolart.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter @Setter
@NoArgsConstructor
public class AddressDTO {

    private Integer id;
    private String cep;
    private String country;
    private String city;
    private String street;
    private String number;
    protected User user;
}
