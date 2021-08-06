package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {

    protected Integer id;
    protected String name;
    protected PersonType personType;
    protected String Phone;
    protected String email;
    protected String password;
    protected Profile profile;
    private String cpf;
    private String about;
    private Integer age;
    private Set<String> skills = new HashSet<>();
}
