package com.epam.lab.beseda.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.epam.lab.beseda.util.DBEntityTable.T_TAG;
import static com.epam.lab.beseda.util.FieldsLimitations.STRING_VALUE;

@Entity
@Table(name = T_TAG)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Tag extends BaseEntity implements Comparable<Tag> {

    @NotBlank
    @Pattern(regexp = STRING_VALUE)
    @NonNull
    private String name;


    public Tag() {
        super();
    }

    public Tag(@NonNull String name) {
        super();
        this.name = name;
    }

    @Override
    public int compareTo(Tag o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
