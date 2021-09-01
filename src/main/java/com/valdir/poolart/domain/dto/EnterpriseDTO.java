package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.Address;
import com.valdir.poolart.domain.Enterprise;
import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EnterpriseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "O campo NOME é mandatório")
    private String name;

    @CNPJ
    @NotEmpty(message = "O campo CNPJ é mandatório")
    private String cnpj;

    private PersonType personType;

    @NotEmpty(message = "O campo PHONE é mandatório")
    private String phone;

    @Email
    @NotEmpty(message = "O campo E-MAIL é mandatório")
    private String email;

    @NotEmpty(message = "O campo PASSWORD é mandatório")
    private String password;

    private Set<Integer> profiles = Collections.singleton(Profile.ENTERPRISE.getCode());

    private String about;
    private Address address;

    public EnterpriseDTO(Enterprise model) {
        this.id = model.getId();
        this.name = model.getName();
        this.phone = model.getPhone();
        this.personType = PersonType.LEGAL;
        this.cnpj = model.getCnpj();
        this.email = model.getEmail();
        this.password = model.getPassword();
        this.about = model.getAbout();
        this.profiles = model.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }
}
