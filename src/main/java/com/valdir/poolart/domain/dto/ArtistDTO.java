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
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {

    protected Integer id;

    @NotEmpty(message = "O campo NOME é mandatório")
    protected String name;

    protected PersonType personType = PersonType.PHYSICAL;

    @NotEmpty(message = "O campo PHONE é mandatório")
    protected String Phone;

    @Email
    @NotEmpty(message = "O campo E-MAIL é mandatório")
    protected String email;

    @NotEmpty(message = "O campo PASSWORD é mandatório")
    protected String password;

    protected Profile profile = Profile.ARTIST;

    @CPF
    @NotEmpty(message = "O campo CPF é mandatório")
    private String cpf;
    private String about;
    private Integer age;
    private Address address;
    private Set<String> skills = new HashSet<>();
}
