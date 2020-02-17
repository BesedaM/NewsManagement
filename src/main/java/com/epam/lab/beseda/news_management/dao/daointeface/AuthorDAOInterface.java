package com.epam.lab.beseda.news_management.dao.daointeface;

import com.epam.lab.beseda.news_management.entity.Author;

import java.util.List;

public interface AuthorDAOInterface extends AbstractDAOInterface<Author>{

    List<Integer> getNewsId(int authorId);
}
