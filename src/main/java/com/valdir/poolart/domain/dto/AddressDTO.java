package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.User;
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
    protected User user;
}
