package com.valdir.poolart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.poolart.domain.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_ADDRESS")
public class Address  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private String country;
    private String city;
    private String street;
    private String number;

    @JsonIgnore
    @OneToOne
    @JoinColumn
    protected User user;

    public Address(AddressDTO dto) {
        this.id = dto.getId();
        this.cep = dto.getCep();
        this.country = dto.getCountry();
        this.city = dto.getCity();
        this.street = dto.getStreet();
        this.number = dto.getNumber();
        this.user.id = dto.getUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;

        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return 1395783287;
    }
}
