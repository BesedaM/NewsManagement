package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.Role;

public interface RoleDAOInterface extends AbstractDAOInterface<Role>{

    Role getEntityByName(String name);
}
