package com.epam.lab.beseda.service.search;

import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void testSearchByTags_tagIsNull() {
        List<NewsDTO> foundNews = searchCriteria.findByTagList(null);

        Mockito.verify(newsMapper, Mockito.never()).toDto(any(News.class));
    }
}
