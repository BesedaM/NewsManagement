package com.epam.lab.beseda.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO{

    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String name, String surname, String login, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserDTO(int id, String name, String surname, String login, String password, String role) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
