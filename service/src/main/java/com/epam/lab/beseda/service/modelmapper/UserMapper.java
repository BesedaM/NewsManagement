package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    public UserMapper() {
        super(User.class, UserDTO.class);
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO userDTO = super.toDto(entity);
        userDTO.setRole(entity.getRole().getName());
        return userDTO;
    }


    @Override
    public User toEntity(UserDTO dto) {
        User user = super.toEntity(dto);
        user.setRole(new Role(dto.getRole()));
        return user;
    }
}
