package com.epam.lab.beseda.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO{

    private String name;

    public RoleDTO() {
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    public RoleDTO(int id, String name) {
        super(id);
        this.name = name;
    }
}
