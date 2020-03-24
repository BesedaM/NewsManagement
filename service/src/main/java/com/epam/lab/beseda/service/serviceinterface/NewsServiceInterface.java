package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.exception.ServiceLayerException;

import java.util.List;

public interface NewsServiceInterface extends AbstractServiceInterface<NewsDTO>{

    List<NewsDTO> getAllSorted(String param) throws ServiceLayerException;

    int getNewsNumber();

    void addNewsTags(int newsId, List<String> tags) throws ServiceLayerException;

    void deleteNewsTags(int newsId, List<String> tags);

    List<TagDTO> getNewsTags(int newsId);

}
