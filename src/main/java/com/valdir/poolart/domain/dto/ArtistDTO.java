package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "O campo NOME é mandatório")
    private String name;

    private PersonType personType = PersonType.PHYSICAL;

    @NotEmpty(message = "O campo PHONE é mandatório")
    private String Phone;

    @Email
    @NotEmpty(message = "O campo E-MAIL é mandatório")
    private String email;

    @NotEmpty(message = "O campo PASSWORD é mandatório")
    private String password;

    private Set<Profile> profiles = Collections.singleton(Profile.ARTIST);

    @CPF
    @NotEmpty(message = "O campo CPF é mandatório")
    private String cpf;
    private String about;
    private Integer age;
    private Address address;
    private Set<String> skills = new HashSet<>();
}
