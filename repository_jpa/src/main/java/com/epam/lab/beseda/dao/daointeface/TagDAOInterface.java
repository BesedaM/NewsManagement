package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.Tag;

public interface TagDAOInterface extends AbstractDAOInterface<Tag>{

    Tag getEntityByName(String name);
}
