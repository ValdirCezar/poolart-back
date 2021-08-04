package com.valdir.poolart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    ARTIST(1, "ROLE_ARTIST"),
    ENTERPRISE(2, "ROLE_ENTERPRISE");

    private Integer code;
    private String description;

    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Profile profile : Profile.values()) {
            if(cod.equals(profile.getCode())) {
                return profile;
            }
        }

        throw new IllegalArgumentException("Inválid code to Profile!");
    }
}
