package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode
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
        this.cpf = cpf;
        this.about = about;
        this.age = age;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }
}
