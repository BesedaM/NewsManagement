package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.RoleDTO;

public interface RoleServiceInterface extends AbstractServiceInterface<RoleDTO>{

    RoleDTO getEntityByName(String name);
}
