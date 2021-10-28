package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.Artist;
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
import java.util.stream.Collectors;

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
    private String phone;

    @Email
    @NotEmpty(message = "O campo E-MAIL é mandatório")
    private String email;

    @NotEmpty(message = "O campo PASSWORD é mandatório")
    private String password;

    private Set<Integer> profiles = Collections.singleton(Profile.ARTIST.getCode());

    private Integer numberOfReviews = 0;
    private Double rating = 0D;
    private Double sumOfReviews = 0D;

    @CPF
    @NotEmpty(message = "O campo CPF é mandatório")
    private String cpf;
    private String about;
    private Integer age;
    private Address address;
    private Set<String> skills = new HashSet<>();

    public ArtistDTO(Artist model) {
        this.id = model.getId();
        this.name = model.getName();
        this.phone = model.getPhone();
        this.cpf = model.getCpf();
        this.personType = model.getPersonType();
        this.email = model.getEmail();
        this.password = model.getPassword();
        this.age = model.getAge();
        this.about = model.getAbout();
        this.skills = model.getSkills();
        this.profiles = model.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.numberOfReviews = model.getNumberOfReviews();
        this.rating = model.getRating();
        this.sumOfReviews = model.getSumOfReviews();
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }
}
