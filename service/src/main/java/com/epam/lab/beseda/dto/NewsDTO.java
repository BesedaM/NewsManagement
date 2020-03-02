package com.epam.lab.beseda.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class NewsDTO extends BaseDTO implements Comparable<NewsDTO> {

    private AuthorDTO author;

    private String title;
    private String shortText;
    private String fullText;
    private LocalDate creationDate;
    private LocalDate modificationDate;

    private Set<String> tags = new HashSet<>();

    public NewsDTO() {
    }

    public NewsDTO(AuthorDTO author, String title, String shortText, String fullText) {
        this.author = author;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
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

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
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

    @Override
    public int compareTo(NewsDTO newsDTO) {
        return this.id - newsDTO.getId();
    }
}
