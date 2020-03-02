package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.configuration.ModelMapperConfiguration;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.MapperException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ModelMapperConfiguration.class})
public class NewsMapperTest {

    @Autowired
    private NewsMapper mapper;

    private News news;
    private NewsDTO newsDTO;

    {
        news = new News("Title", "Short_text", "Full_text", new Author("Aleksei", "Petrov"));
        newsDTO = new NewsDTO(new AuthorDTO("Aleksei", "Petrov"), "Title", "Short_text", "Full_text");
    }

    @Test
    public void testToDto() {
        NewsDTO newsDTO = mapper.toDto(news);
        Assert.assertEquals(news.getTitle(), newsDTO.getTitle());
    }


    @Test
    public void testToEntity() throws MapperException {
        NewsDTO newsDTO = mapper.toDto(news);
        News newsCreated = mapper.toEntity(newsDTO);
        Assert.assertEquals(newsDTO.getTitle(), newsCreated.getTitle());
    }

}
