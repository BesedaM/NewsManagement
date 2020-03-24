package com.epam.lab.beseda.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

import static com.epam.lab.beseda.util.DBEntityTable.SCHEMA_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.T_ROLES;
import static com.epam.lab.beseda.util.FieldsLimitations.STRING_VALUE;

@Entity
@Table(name = T_ROLES)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Role extends BaseEntity {

    @NotBlank
    @Pattern(regexp = STRING_VALUE)
    @NonNull
    @Column(unique = true)
    private String name;

    public Role() {
        super();
    }

    public Role(@NonNull String name) {
        this.name = name;
    }

    public Role(int id, @NonNull String name) {
        super(id);
        this.name = name;
    }
}
