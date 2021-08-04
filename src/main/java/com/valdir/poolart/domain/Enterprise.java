package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ENTERPRISE")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Enterprise that = (Enterprise) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 810565874;
    }
}
