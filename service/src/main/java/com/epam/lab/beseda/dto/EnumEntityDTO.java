package com.epam.lab.beseda.dto;

import java.util.Objects;

public class EnumEntityDTO extends BaseDTO{

    private String name;

    public EnumEntityDTO() {
    }

    public EnumEntityDTO(String name) {
        this.name = name;
    }

    public EnumEntityDTO(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnumEntityDTO)) return false;
        if (!super.equals(o)) return false;
        EnumEntityDTO enumEntityDTO = (EnumEntityDTO) o;
        return Objects.equals(name, enumEntityDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
