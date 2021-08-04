package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ENTERPRISE")
public class Enterprise extends User{
    private static final long serialVersionUID = 1L;

    private String about;

    public Enterprise(Integer id, String name, String cpfOrCnpj, String Phone, String email, String password, Profile profile, String about) {
        super(id, name, cpfOrCnpj, Phone, email, password, profile);
        this.about = about;
    }
}
