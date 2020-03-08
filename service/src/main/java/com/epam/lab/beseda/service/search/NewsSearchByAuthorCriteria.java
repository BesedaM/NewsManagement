package com.epam.lab.beseda.service.search;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsSearchByAuthorCriteria extends NewsSearchCriteria {

    @Autowired
    @Qualifier("authorMapper")
    private AuthorMapper authorMapper;

    public List<NewsDTO> findByAuthor(AuthorDTO author) {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        if (author != null
                && author.getName() != null
                && author.getSurname() != null) {
            List<News> newsList = dao.getNewsByAuthor(authorMapper.toEntity(author));
            for (News nn : newsList) {
                newsDTOList.add(mapper.toDto(nn));
            }
        }
        return newsDTOList;
    }
}
