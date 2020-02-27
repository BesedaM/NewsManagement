package com.epam.lab.beseda.entity;

import java.util.Objects;

public class EnumEntity extends BaseEntity{
    private String name;

    public EnumEntity() {
    }

    public EnumEntity(String name) {
        this.name = name;
    }

    public EnumEntity(int id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnumEntity)) return false;
        if (!super.equals(o)) return false;
        EnumEntity that = (EnumEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return name;
    }
}
