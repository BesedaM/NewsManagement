package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.dto.NewsDTO;

import java.util.List;

public interface NewsServiceInterface extends AbstractServiceInterface<NewsDTO>{

    int getNewsNumber();

    void addNewsTag(int newsId, int tagId);

    void deleteNewsTag(int newsId, int tagId);

    List<String> getNewsTagsNames(int newsId);

    List<EnumEntityDTO> getNewsTags(int newsId);

    void addAuthor(int newsId, int authorId);

    AuthorDTO getAuthor(int newsId);

    List<NewsDTO> getNewsByTagId(int tagId);


}
