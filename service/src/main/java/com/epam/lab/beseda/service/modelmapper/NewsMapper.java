package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper extends AbstractMapper<News, NewsDTO> {

    public NewsMapper() {
        super(News.class, NewsDTO.class);
    }

}
