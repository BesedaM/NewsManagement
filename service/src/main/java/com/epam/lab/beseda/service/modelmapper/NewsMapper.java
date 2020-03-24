package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class NewsMapper extends AbstractMapper<News, NewsDTO> {

    public NewsMapper() {
        super(News.class, NewsDTO.class);
    }

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private TagMapper tagMapper;


    @Override
    public NewsDTO toDto(News entity) {
        Author author = entity.getAuthor();
        author.setNewsList(new ArrayList<>());
        return super.toDto(entity);
    }


}
