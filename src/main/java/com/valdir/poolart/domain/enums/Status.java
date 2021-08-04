package com.valdir.poolart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    OPEN(0, "OPEN"),
    ACCEPTED(1, "ACCEPTED"),
    IN_PROGRESS(2,"IN_PROGRESS"),
    CANCELED(3, "CENCELED"),
    CLOSED(4, "CLOSED");

    private Integer code;
    private String description;

    public static Status toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }
        for(Status status : Status.values()) {
            if(cod.equals(status.getCode())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status code invalid!");
    }
}
