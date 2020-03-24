package com.epam.lab.beseda.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_text() {
        return short_text;
    }

    public void setShort_text(String short_text) {
        this.short_text = short_text;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public void setTags(TreeSet<String> tags) {
        this.tags = tags;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getModification_date() {
        return modification_date;
    }

    public void setModification_date(LocalDate modification_date) {
        this.modification_date = modification_date;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public Set<String> getTags() {
        return tags;
    }
}
