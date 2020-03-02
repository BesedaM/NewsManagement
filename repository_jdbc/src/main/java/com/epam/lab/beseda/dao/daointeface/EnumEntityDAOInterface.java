package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.EnumEntity;

public interface EnumEntityDAOInterface extends AbstractDAOInterface<EnumEntity>{

    EnumEntity getEntityByName(String name);
}