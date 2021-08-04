package com.valdir.poolart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ARTIST")
public class Artist extends User{
    private static final long serialVersionUID = 1L;

    private String about;
    private Integer age;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SKILLS")
    private Set<String> skills = new HashSet<>();

    public void addSkill(String skill) {
        this.skills.add(skill);
    }
}
