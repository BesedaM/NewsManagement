package com.epam.lab.beseda.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class TagDTO extends BaseDTO{

    private String name;

    public TagDTO() {
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public TagDTO(int id, String name) {
        super(id);
        this.name = name;
    }
}
