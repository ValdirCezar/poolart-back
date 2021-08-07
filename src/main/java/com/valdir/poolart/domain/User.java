package com.valdir.poolart.domain;

import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "TB_USER")
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String name;

    protected PersonType personType;

    @Column(unique = true)
    protected String phone;

    @Column(unique = true)
    protected String email;
    protected String password;
    protected Profile profile;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    protected Address address;

    public User(Integer id, String name, String phone, String email, String password, Profile profile) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
