package com.epam.lab.beseda.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.DBEntityTable.NEWS_ID;
import static com.epam.lab.beseda.util.FieldsLimitations.*;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = T_NEWS)
@Setter
@Getter
@EqualsAndHashCode(exclude = {"full_text", "modification_date"}, callSuper = true)
@ToString(exclude = "full_text")
public class News extends BaseEntity {

    @NotBlank
    @Size(max = MAX_TITLE_LENGTH)
    @NotNull
    private String title;

    @NotBlank
    @Size(max = MAX_SHORT_TEXT_LENGTH)
    @NotNull
    private String short_text;

    @NotBlank
    @Size(max = MAX_FULL_TEXT_LENGTH)
    @NotNull
    private String full_text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creation_date = LocalDate.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate modification_date = LocalDate.now();

    @NotNull
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = T_NEWS_AUTHOR, joinColumns = @JoinColumn(name = NEWS_ID),
            inverseJoinColumns = @JoinColumn(name = AUTHOR_ID))
    private Author author;

    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToMany(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = T_NEWS_TAG, joinColumns = @JoinColumn(name = NEWS_ID),
            inverseJoinColumns = @JoinColumn(name = TAG_ID))
    private Set<Tag> tags = new TreeSet<>();


    public News() {
        super();
    }

    public News(String title, String short_text, String full_text) {
        super();
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.creation_date = LocalDate.now();
        this.modification_date = this.creation_date;
    }

    public News(String title, String short_text, String full_text, Author author) {
        super();
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.author = author;
        this.creation_date = LocalDate.now();
        this.modification_date = this.creation_date;
    }

    public News(String title, String short_text, String full_text, LocalDate creationDate) {
        super();
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.creation_date = creationDate;
        this.modification_date = this.creation_date;
    }

}
