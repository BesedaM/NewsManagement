package com.epam.lab.beseda.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.FieldsLimitations.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = T_AUTHOR)
@Setter
@Getter
@EqualsAndHashCode(exclude = "newsList", callSuper = true)
@ToString(exclude = "newsList")
public class Author extends BaseEntity {

    @NotBlank
    @Size(min = MIN_NAME_LENGTH, max = MAX_AUTHOR_NAME_LENGTH)
    @Pattern(regexp = STRING_VALUE)
    @NonNull
    private String name;

    @NotBlank
    @Size(min = MIN_SURNAME_LENGTH, max = MAX_AUTHOR_SURNAME_LENGTH)
    @Pattern(regexp = STRING_VALUE)
    @NonNull
    private String surname;


    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DELETE})
    @OneToMany(cascade = REMOVE, orphanRemoval = true, fetch = FetchType.EAGER,targetEntity = News.class)
    @JoinTable(name = T_NEWS_AUTHOR, joinColumns = @JoinColumn(name = AUTHOR_ID), inverseJoinColumns = @JoinColumn(name = NEWS_ID))
    private List<News> newsList = new ArrayList<>();

    public Author() {
        super();
    }

    public Author(@NonNull String name, @NonNull String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }

    public Author(int id, @NonNull String name, @NonNull String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

}
