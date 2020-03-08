package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.RoleDTO;
import com.epam.lab.beseda.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDTO> {

    public RoleMapper() {
        super(Role.class, RoleDTO.class);
    }
}
