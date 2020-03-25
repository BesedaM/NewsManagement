package com.epam.lab.beseda.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.TreeSet;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class NewsDTO extends BaseDTO {

    private AuthorDTO author;

    private String title;
    private String short_text;
    private String full_text;
    private LocalDate creation_date;
    private LocalDate modification_date;

    private TreeSet<String> tags = new TreeSet<>();

    public NewsDTO() {
    }

    public NewsDTO(AuthorDTO author, String title, String shortText, String fullText) {
        this.author = author;
        this.title = title;
        this.short_text = shortText;
        this.full_text = fullText;
    }
}
