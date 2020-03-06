package com.epam.lab.beseda.daointeface;

import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.User;

public interface UserDAOInterface extends AbstractDAOInterface<User>{

    User getUserByLoginAndPassword(String login, String password);

    User getUserByLogin(String login);

    void setRole(int userId, int roleId);

    EnumEntity getRole(int userId);

}
