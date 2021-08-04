package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ENTERPRISE")
public class Enterprise extends User{
    private static final long serialVersionUID = 1L;

    private String about;

    @CNPJ
    @Column(unique = true)
    private String cnpj;

    public Enterprise(Integer id, String name, PersonType personType, String Phone, String email, String password, Profile profile, String about, String cnpj) {
        super(id, name, personType, Phone, email, password, profile);
        this.about = about;
        this.cnpj = cnpj;
    }
}
