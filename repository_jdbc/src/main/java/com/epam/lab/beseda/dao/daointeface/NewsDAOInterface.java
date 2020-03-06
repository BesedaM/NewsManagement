package com.epam.lab.beseda.daointeface;

import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.ParameterNotExistsException;

import java.util.List;

public interface NewsDAOInterface extends AbstractDAOInterface<News>{

    List<News> getAllSorted(String param) throws ParameterNotExistsException;

    List<News> getNewsByAuthor(Author author);

    void addNewsTag(int newsId, int tagId);

    void deleteNewsTag(int newsId, int tagId);

    List<String> getNewsTagsNames(int newsId);

    List<EnumEntity> getNewsTags(int newsId);

    void addAuthor(int newsId, int authorId);

    int getAuthorId(int newsId);

    List<Integer> getNewsId(int tagId);

    int getNewsNumber();
}
