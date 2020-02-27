package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    public UserMapper() {
        super(User.class, UserDTO.class);
    }

}
