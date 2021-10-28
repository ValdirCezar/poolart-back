package com.valdir.poolart.domain;

import com.valdir.poolart.domain.dto.EnterpriseDTO;
import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.stream.Collectors;

@Getter @Setter
@Entity(name = "ENTERPRISE")
public class Enterprise extends User{
    private static final long serialVersionUID = 1L;

    private String about;

    @Column(unique = true)
    private String cnpj;

    public Enterprise() {
        addProfile(Profile.ENTERPRISE);
    }

    public Enterprise(Integer id, String name, String phone, String email, String password, String about, String cnpj) {
        super(id, name, phone, email, password);
        setPersonType(PersonType.LEGAL);
        addProfile(Profile.ENTERPRISE);
        this.about = about;
        this.cnpj = cnpj;
    }

    public Enterprise(EnterpriseDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.personType = dto.getPersonType();
        this.cnpj = dto.getCnpj();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.about = dto.getAbout();
        this.profiles = dto.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.numberOfReviews = dto.getNumberOfReviews();
        this.rating = dto.getRating();
        this.sumOfReviews = dto.getSumOfReviews();
    }

}
