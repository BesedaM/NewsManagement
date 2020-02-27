package com.epam.lab.beseda.dto;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class NewsDTO extends BaseDTO {

    private String authorName;
    private String authorSurname;

    private String title;
    private String shortText;
    private String fullText;
    private GregorianCalendar creationDate;
    private GregorianCalendar modificationDate;

    private List<String> tags = new ArrayList<>();

    public NewsDTO() {
    }

    public NewsDTO(String authorName, String authorSurname, String title, String shortText, String fullText) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
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

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public GregorianCalendar getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(GregorianCalendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public List<String> getTags() {
        return tags;
    }
}
