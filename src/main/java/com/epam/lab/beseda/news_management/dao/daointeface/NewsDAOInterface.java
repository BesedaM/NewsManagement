package com.epam.lab.beseda.news_management.dao.daointeface;

import com.epam.lab.beseda.news_management.entity.EnumEntity;
import com.epam.lab.beseda.news_management.entity.News;

import java.util.List;

public interface NewsDAOInterface extends AbstractDAOInterface<News>{

    void addNewsTag(int newsId, int tagId);

    void deleteNewsTag(int newsId, int tagId);

    List<String> getNewsTagsNames(int newsId);

    List<EnumEntity> getNewsTags(int newsId);

    void addAuthor(int newsId, int authorId);

    int getAuthorId(int newsId);

    List<Integer> getNewsId(int tagId);

    int getNewsNumber();
}
