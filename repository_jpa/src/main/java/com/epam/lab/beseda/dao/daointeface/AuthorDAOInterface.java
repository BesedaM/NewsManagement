package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.Author;

public interface AuthorDAOInterface extends AbstractDAOInterface<Author>{

    Author getAuthorByNameSurname(String name, String surname);
}
