package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.User;

public interface UserDAOInterface extends AbstractDAOInterface<User>{

    User getUserByLoginAndPassword(String login, String password);

    User getUserByLogin(String login);

}
