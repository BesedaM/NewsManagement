package com.epam.lab.beseda.entity;

import java.util.*;

public class News extends BaseEntity {
    private String title;
    private String short_text;
    private String full_text;
    private GregorianCalendar creationDate;
    private GregorianCalendar modificationDate;
    private Author author;
    private List<EnumEntity> tagList;

    public News() {
    }

    public News(String title, String short_text, String full_text) {
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.creationDate = new GregorianCalendar();
        this.modificationDate = this.creationDate;
    }

    public News(String title, String short_text, String full_text, Author author) {
        this.title = title;
        this.short_text = short_text;
        this.full_text = full_text;
        this.author = author;
        this.creationDate = new GregorianCalendar();
        this.modificationDate = this.creationDate;
    }

    public News(String title, String short_text, String full_text, GregorianCalendar creationDate) {
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

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setModificationDate(GregorianCalendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setTagList(List<EnumEntity> tagList) {
        this.tagList = tagList;
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

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public GregorianCalendar getModificationDate() {
        return modificationDate;
    }

    public List<EnumEntity> getTagList() {
        return tagList;
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
