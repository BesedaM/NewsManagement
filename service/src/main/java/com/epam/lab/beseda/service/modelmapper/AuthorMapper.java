package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

    @Autowired
    private NewsMapper newsMapper;

    public AuthorMapper() {
        super(Author.class, AuthorDTO.class);
    }

    @Override
    public AuthorDTO toDto(Author entity) {
        for (News news:entity.getNewsList()) {
            news.setAuthor(null);
        }
        return super.toDto(entity);
    }
}
