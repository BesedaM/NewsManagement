package com.epam.lab.beseda.service.search;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsSearchByTagsCriteria extends NewsSearchCriteria {

    public List<NewsDTO> findByTagList(List<String> tags) {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        if (tags != null && tags.size() > 0) {
            List<News> newsList = dao.findByTagsList(tags);
            for (News news : newsList) {
                newsDTOList.add(mapper.toDto(news));
            }
        }
        return newsDTOList;
    }
}
