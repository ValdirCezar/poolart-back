package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_OFFER")
public class Offer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private LocalDate date;
    private Float price;
    private Status status;
    private String observations;

    @ManyToOne
    @JoinColumn
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Offer offer = (Offer) o;

        return Objects.equals(id, offer.id);
    }

    @Override
    public int hashCode() {
        return 548336748;
    }
}
