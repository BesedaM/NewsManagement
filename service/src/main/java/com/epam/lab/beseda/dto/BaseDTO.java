package com.epam.lab.beseda.dto;

import java.util.Objects;

public abstract class BaseDTO {
    private int id;

    public BaseDTO() {
    }

    public BaseDTO(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDTO)) return false;
        BaseDTO that = (BaseDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
