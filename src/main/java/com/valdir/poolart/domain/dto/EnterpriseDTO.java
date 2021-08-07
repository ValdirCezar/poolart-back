package com.valdir.poolart.domain.dto;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter @Setter
public class EnterpriseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "O campo NOME é mandatório")
    private String name;

    @NotEmpty(message = "O campo CNPJ é mandatório")
    private String cnpj;

    private PersonType personType = PersonType.LEGAL;

    @NotEmpty(message = "O campo PHONE é mandatório")
    private String Phone;

    @Email
    @NotEmpty(message = "O campo E-MAIL é mandatório")
    private String email;

    @NotEmpty(message = "O campo PASSWORD é mandatório")
    private String password;

    private Profile profile = Profile.ENTERPRISE;
    private String about;

}
