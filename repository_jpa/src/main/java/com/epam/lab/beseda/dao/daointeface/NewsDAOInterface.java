package com.epam.lab.beseda.dao.daointeface;

import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.ParameterNotExistsException;

import java.util.List;

public interface NewsDAOInterface extends AbstractDAOInterface<News>{

    List<News> getAllSorted(String param) throws ParameterNotExistsException;

    List<News> getNewsByAuthor(Author author);

    void addNewsTag(News news,Tag tag);

    void deleteNewsTag(int newsId, int tagId);

    List<News> findByTagsList(List<String> tagsNames);

    int getNewsNumber();
}
