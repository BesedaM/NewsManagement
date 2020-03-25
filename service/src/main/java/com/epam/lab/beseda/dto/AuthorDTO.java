package com.epam.lab.beseda.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class AuthorDTO extends BaseDTO {

    private String name;
    private String surname;

    private List<NewsDTO> newsList;

    {
        newsList = new ArrayList<>();
    }

    public AuthorDTO() {
    }

    public AuthorDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AuthorDTO(int id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

}
