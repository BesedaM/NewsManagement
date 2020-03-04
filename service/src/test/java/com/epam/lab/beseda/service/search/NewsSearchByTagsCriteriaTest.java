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
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class NewsSearchByTagsCriteriaTest {

    @Mock
    private NewsDAO newsDao;

    @Mock
    private NewsMapper newsMapper;

    @InjectMocks
    private NewsSearchByTagsCriteria searchCriteria;

    @Test
    public void testSearchByTags_tagProvided() {
        List<String> tags = new ArrayList<>();
        tags.add("123");
        tags.add("too");

        List<News> newsList = new ArrayList<>();
        News news01 = new News();
        news01.setTags(new HashSet<>(tags));
        newsList.add(news01);
        newsList.add(new News());


        Mockito.when(newsDao.getAll()).thenReturn(newsList);
        Mockito.when(newsMapper.toDto(any(News.class))).thenReturn(new NewsDTO());

        List<NewsDTO> foundNews = searchCriteria.findByTagList(tags);

        Mockito.verify(newsMapper, Mockito.atMostOnce()).toDto(any(News.class));
    }


    @Test
    public void testSearchByTags_tagIsNull() {
        List<NewsDTO> foundNews = searchCriteria.findByTagList(null);

        Mockito.verify(newsMapper, Mockito.never()).toDto(any(News.class));
    }
}
