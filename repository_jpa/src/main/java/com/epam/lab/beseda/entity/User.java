package com.epam.lab.beseda.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.FieldsLimitations.*;

@Entity
@Table(name = T_USER)
@Setter
@Getter
@EqualsAndHashCode(exclude = "role", callSuper = true)
@ToString
public class User extends BaseEntity {

    @NotBlank
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    @Pattern(regexp = STRING_VALUE)
    @NonNull
    private String name;

    @NotBlank
    @Size(min = MIN_NAME_LENGTH, max = MAX_SURNAME_LENGTH)
    @Pattern(regexp = STRING_VALUE)
    @NonNull
    private String surname;

    @NotBlank
    @Size(min = MIN_LOGIN_LENGTH, max = MAX_LOGIN_LENGTH)
    @Pattern(regexp = ALPHANUMERIC_VALUE)
    @NonNull
    private String login;

    @NotBlank
    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
    @Pattern(regexp = ALPHANUMERIC_VALUE)
    @NonNull
    private String password;

    @NonNull
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = USER_ROLE_ID)
    private Role role;

    public User() {
        super();
    }

    public User(String name, String surname, String login, String password) {
        super();
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public User(String name, String surname, String login, String password, Role role) {
        super();
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id, String name, String surname, String login, String password) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

}
