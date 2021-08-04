package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ARTIST")
public class Artist extends User{
    private static final long serialVersionUID = 1L;

    @CPF
    @Column(unique = true)
    private String cpf;
    private String about;
    private Integer age;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_SKILLS")
    private Set<String> skills = new HashSet<>();

    public Artist(Integer id, String name, PersonType personType, String Phone, String email, String password, Profile profile, String cpf, String about, Integer age) {
        super(id, name, personType, Phone, email, password, profile);
        this.setPersonType(PersonType.PHYSICAL);
        this.cpf = cpf;
        this.about = about;
        this.age = age;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;

        return Objects.equals(getId(), artist.getId());
    }

    @Override
    public int hashCode() {
        return 787003919;
    }
}
