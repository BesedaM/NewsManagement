package com.epam.lab.beseda.news_management.entity;

import java.util.Objects;

public class EnumEntity extends BaseEntity{
    private String value;

    public EnumEntity() {
    }

    public EnumEntity(String value) {
        this.value = value;
    }

    public EnumEntity(int id, String value) {
        super(id);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnumEntity)) return false;
        if (!super.equals(o)) return false;
        EnumEntity that = (EnumEntity) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return  value;
    }
}
