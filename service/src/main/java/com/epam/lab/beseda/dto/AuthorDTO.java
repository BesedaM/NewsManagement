package com.epam.lab.beseda.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNewsList(List<NewsDTO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsDTO> getNewsList() {
        return newsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorDTO)) return false;
        if (!super.equals(o)) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(name, authorDTO.name) &&
                Objects.equals(surname, authorDTO.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname);
    }
}
