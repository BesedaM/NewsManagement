package com.epam.lab.beseda.service.search;

import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class NewsSearchByAuthorCriteriaTest {

    @Mock
    private NewsDAO newsDao;

    @Mock
    private NewsMapper newsMapper;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private NewsSearchByAuthorCriteria searchCriteria;

    @Test
    public void testSearchByAuthor_authorProvided() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());

        Mockito.when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(new Author());
        Mockito.when(newsDao.getNewsByAuthor(any(Author.class))).thenReturn(newsList);
        Mockito.when(newsMapper.toDto(any(News.class))).thenReturn(new NewsDTO());

        List<NewsDTO> foundNews = searchCriteria.findByAuthor(new AuthorDTO());

        Mockito.verify(newsMapper, Mockito.times(2)).toDto(any(News.class));
    }


    @Test
    public void testSearchByAuthor_authorIsNull() {
        List<NewsDTO> foundNews = searchCriteria.findByAuthor(null);

        Mockito.verify(newsDao, Mockito.never()).getNewsByAuthor(any(Author.class));
    }

}
