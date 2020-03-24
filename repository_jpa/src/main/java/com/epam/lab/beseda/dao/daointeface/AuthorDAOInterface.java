package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.Author;

import java.util.List;

public interface AuthorDAOInterface extends AbstractDAOInterface<Author>{

    Author getAuthorByNameSurname(String name, String surname);

//    List<Integer> getNewsId(int authorId);
}
