package com.epam.lab.beseda.entity;

import java.time.LocalDate;
import java.util.*;

public class News extends BaseEntity {
    private String title;
    private String short_text;
    private String full_text;
    private LocalDate creationDate;
    private LocalDate modificationDate;
    private Author author;
    private Set<String> tags;

    {
        tags = new HashSet<>();
    }

    public News() {
    }

    public News(String title, String short_text, String full_text) {
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.creationDate = LocalDate.now();
        this.modificationDate = this.creationDate;
    }

    public News(String title, String short_text, String full_text, Author author) {
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.author = author;
        this.creationDate = LocalDate.now();
        this.modificationDate = this.creationDate;
    }

    public News(String title, String short_text, String full_text, LocalDate creationDate) {
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.creationDate = creationDate;
        this.modificationDate = this.creationDate;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortText(String short_text) {
        this.short_text = short_text;
    }

    public void setFullText(String full_text) {
        this.full_text = full_text;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getShortText() {
        return short_text;
    }

    public String getFullText() {
        return full_text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        if (!super.equals(o)) return false;
        News news = (News) o;
        return title.equals(news.title) &&
                short_text.equals(news.short_text) &&
                creationDate.equals(news.creationDate) &&
                modificationDate.equals(news.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, short_text, creationDate, modificationDate);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", short_text='" + short_text + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
