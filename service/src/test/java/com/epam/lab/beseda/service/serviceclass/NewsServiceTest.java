package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AuthorDAO;
import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.validator.NewsValidator;
import com.epam.lab.beseda.service.validator.TagValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

    private static List<News> newsList;
    private static List<NewsDTO> newsDTOList;
    private static AuthorDTO authorDTO;
    private static News news;
    private static NewsDTO newsDTO;

    @Mock
    private AuthorDAO authorDAO;

    @Mock
    private NewsMapper mapper;

    @Mock
    private TagValidator tagValidator;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private EnumEntityMapper tagMapper;

    @Mock
    private NewsDAO newsDao;

    @Mock
    private NewsValidator validator;

    @InjectMocks
    private NewsService service;

    @BeforeClass
    public static void init() {
        newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());
        newsList.add(new News());

        news = new News("title", "short_title", "full_text");

        newsDTOList = new ArrayList<>();
        newsDTOList.add(new NewsDTO());
        newsDTOList.add(new NewsDTO());
        newsDTOList.add(new NewsDTO());

        authorDTO = new AuthorDTO("Egor", "Zhuk");
        newsDTO = new NewsDTO(authorDTO, "title", "short_title", "full_text");

    }


    @Test
    public void testGetAll() {
        Mockito.when(newsDao.getAll()).thenReturn(newsList);
        Mockito.when(mapper.toDto(any(News.class))).thenReturn(new NewsDTO());
        Mockito.when(authorDAO.getEntityById(anyInt())).thenReturn(new Author());
        Mockito.when(authorMapper.toDto(any(Author.class))).thenReturn(new AuthorDTO());
        Mockito.when(newsDao.getNewsTagsNames(anyInt())).thenReturn(new ArrayList<>());

        List<NewsDTO> receivedNews = service.getAll();
        Mockito.verify(newsDao, times(1)).getAll();
        Assert.assertEquals(receivedNews.size(), 3);
    }


    @Test
    public void testGetEntityById() {
        int id = 1;
        Mockito.when(newsDao.getEntityById(id)).thenReturn(news);
        Mockito.when(mapper.toDto(any(News.class))).thenReturn(newsDTO);
        Mockito.when(authorMapper.toDto(any(Author.class))).thenReturn(new AuthorDTO());
        Mockito.when(authorDAO.getEntityById(anyInt())).thenReturn(new Author());
        Mockito.when(newsDao.getAuthorId(anyInt())).thenReturn(10);

        NewsDTO receivedNews = service.getDtoById(id);
        Mockito.verify(newsDao, times(1)).getEntityById(id);
        Assert.assertEquals(newsDTO, receivedNews);
    }

    @Test
    public void testDelete() {
        int id = 1;
        Mockito.doNothing().when(newsDao).delete(id);
        service.delete(id);
        Mockito.verify(newsDao, atMostOnce()).delete(id);
    }

    @Test
    public void testAdd_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(isA(NewsDTO.class));
        Mockito.when(newsDao.add(isA(News.class))).thenReturn(1);
        Mockito.doNothing().when(newsDao).addAuthor(anyInt(), anyInt());
        Mockito.when(mapper.toEntity(any(NewsDTO.class))).thenReturn(news);
        Mockito.when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(new Author());

        service.add(newsDTO);
        Mockito.verify(newsDao, times(1)).add(news);
        Mockito.verify(validator, times(1)).validate(any(NewsDTO.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testAdd_incorrectData() throws ServiceLayerException, DAOLayerException {
        Mockito.doThrow(new ValidationException()).when(validator).validate(any(NewsDTO.class));

        service.add(newsDTO);
        Mockito.verify(newsDao, never()).add(news);
        Mockito.verify(validator, times(1)).validate(any(NewsDTO.class));
    }

    @Test
    public void testUpdate_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(any(NewsDTO.class));
        Mockito.doNothing().when(newsDao).update(any(News.class));
        Mockito.when(mapper.toEntity(any(NewsDTO.class))).thenReturn(news);
        Mockito.when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(new Author());

        service.update(newsDTO);
        Mockito.verify(validator, times(1)).validate(any(NewsDTO.class));
        Mockito.verify(newsDao, times(1)).update(any(News.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testUpdate_incorrectData() throws DAOLayerException, ServiceLayerException {
        Mockito.doThrow(ValidationException.class).when(validator).validate(any(NewsDTO.class));
        service.update(newsDTO);
        Mockito.verify(validator, times(1)).validate(any(NewsDTO.class));
        Mockito.verify(newsDao, never()).update(news);
    }

    @Test
    public void testGetNewsNumber() {
        int newsNum = 10;
        Mockito.when(newsDao.getNewsNumber()).thenReturn(newsNum);
        Assert.assertEquals(newsNum, service.getNewsNumber());
    }

//    @Test
//    public void testAddNewsTag() {
//        Mockito.doNothing().when(newsDao).addNewsTag(anyInt(), anyInt());
//        service.addNewsTag(1, 2);
//        Mockito.verify(newsDao).addNewsTag(anyInt(), anyInt());
//    }

//    @Test
//    public void testDeleteNewsTag() {
//        Mockito.doNothing().when(newsDao).deleteNewsTag(anyInt(), anyInt());
//        service.deleteNewsTag(1, 2);
//        Mockito.verify(newsDao).deleteNewsTag(anyInt(), anyInt());
//    }

    @Test
    public void getNewsTagsNames() {
        int id = 1;
        List<String> newsTagsNames = new ArrayList<>();
        newsTagsNames.add("tag1");
        newsTagsNames.add("tag2");

        Mockito.when(newsDao.getNewsTagsNames(id)).thenReturn(newsTagsNames);

        List<String> tagsNames = service.getNewsTagsNames(id);
        verify(newsDao, atMostOnce()).getNewsTagsNames(id);
        Assert.assertEquals(newsTagsNames, tagsNames);
    }

    @Test
    public void getNewsTags() {
        int id = 1;
        List<EnumEntityDTO> newsTags = new ArrayList<>();
        newsTags.add(new EnumEntityDTO());
        newsTags.add(new EnumEntityDTO());

        List<EnumEntity> newsTagsList = new ArrayList<>();
        newsTagsList.add(new EnumEntity());
        newsTagsList.add(new EnumEntity());

        Mockito.when(newsDao.getNewsTags(id)).thenReturn(newsTagsList);
        Mockito.when(tagMapper.toDto(any(EnumEntity.class))).thenReturn(new EnumEntityDTO());

        List<EnumEntityDTO> tags = service.getNewsTags(id);
        verify(newsDao, atMostOnce()).getNewsTagsNames(id);
        Assert.assertEquals(newsTags, tags);
    }

    @Test
    public void testAddAuthor() {
        Mockito.doNothing().when(newsDao).addAuthor(anyInt(), anyInt());
        service.addAuthor(1, 2);
        verify(newsDao, atMostOnce()).addAuthor(anyInt(), anyInt());
    }

    @Test
    public void getAuthor() {
        int newsId = 1;
        int authorId = 2;
        AuthorDTO authorDTO = new AuthorDTO();

        when(newsDao.getAuthorId(newsId)).thenReturn(authorId);
        when(authorDAO.getEntityById(authorId)).thenReturn(new Author());
        Mockito.when(authorMapper.toDto(any(Author.class))).thenReturn(authorDTO);

        AuthorDTO receivedAuthor = service.getAuthor(newsId);

        verify(newsDao, atMostOnce()).getAuthorId(anyInt());
        verify(authorDAO, atMostOnce()).getEntityById(anyInt());

        Assert.assertEquals(authorDTO, receivedAuthor);
    }

    @Test
    public void testGetNewsByTagId() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);

        int tagId = 1;

        when(newsDao.getNewsId(tagId)).thenReturn(idList);
        when(newsDao.getEntityById(anyInt())).thenReturn(new News());

        List<NewsDTO> newsList = service.getNewsByTagId(tagId);

        verify(newsDao, atMostOnce()).getNewsId(anyInt());
        verify(newsDao, times(3)).getEntityById(anyInt());
    }

}
