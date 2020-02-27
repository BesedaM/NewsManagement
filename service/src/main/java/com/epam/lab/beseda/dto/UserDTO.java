package com.epam.lab.beseda.dto;

import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        if (!super.equals(o)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) &&
                Objects.equals(surname, userDTO.surname) &&
                Objects.equals(login, userDTO.login) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, login, password, role);
    }
}
