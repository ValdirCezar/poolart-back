package com.valdir.poolart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonType {

    PHYSICAL(0, "PHYSICAL"), LEGAL(1, "LEGAL");

    private Integer code;
    private String description;

    public static PersonType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (PersonType type : PersonType.values()) {
            if(cod.equals(type.getCode())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code to person type!");
    }
}
