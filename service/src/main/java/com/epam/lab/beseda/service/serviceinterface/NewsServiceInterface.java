package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.exception.ServiceLayerException;

import java.util.List;

public interface NewsServiceInterface extends AbstractServiceInterface<NewsDTO>{

    List<NewsDTO> getAllSorted(String param) throws ServiceLayerException;

    int getNewsNumber();

    void addNewsTags(int newsId, List<String> tags) throws ServiceLayerException;

    void deleteNewsTags(int newsId, List<String> tags);

    List<String> getNewsTagsNames(int newsId);

    List<EnumEntityDTO> getNewsTags(int newsId);

    void addAuthor(int newsId, int authorId);

    AuthorDTO getAuthor(int newsId);

    List<NewsDTO> getNewsByTagId(int tagId);


}
