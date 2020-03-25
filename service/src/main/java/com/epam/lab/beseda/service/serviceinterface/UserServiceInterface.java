package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.UserDTO;

public interface UserServiceInterface extends AbstractServiceInterface<UserDTO>{

    UserDTO getUserByLoginAndPassword(String login, String password);

    UserDTO getUserByLogin(String login);
}
