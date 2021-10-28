package com.valdir.poolart.domain;

import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
@Entity(name = "ARTIST")
public class Artist extends User{
    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    private String cpf;
    private String about;
    private Integer age;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_SKILLS")
    private Set<String> skills = new HashSet<>();

    public Artist() {
        addProfile(Profile.ARTIST);
    }

    public Artist(Integer id, String name, String phone, String email, String password, String cpf, String about, Integer age) {
        super(id, name, phone, email, password);
        setPersonType(PersonType.PHYSICAL);
        addProfile(Profile.ARTIST);
        this.cpf = cpf;
        this.about = about;
        this.age = age;
    }

    public Artist(ArtistDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.cpf = dto.getCpf();
        this.personType = dto.getPersonType();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.age = dto.getAge();
        this.about = dto.getAbout();
        this.skills = dto.getSkills();
        this.profiles = dto.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.numberOfReviews = dto.getNumberOfReviews();
        this.rating = dto.getRating();
        this.sumOfReviews = dto.getSumOfReviews();
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

}
