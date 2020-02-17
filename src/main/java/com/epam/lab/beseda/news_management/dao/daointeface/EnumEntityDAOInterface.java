package com.epam.lab.beseda.news_management.dao.daointeface;

import com.epam.lab.beseda.news_management.entity.EnumEntity;

public interface EnumEntityDAOInterface extends AbstractDAOInterface<EnumEntity>{

    EnumEntity getEntityByName(String name);
}
