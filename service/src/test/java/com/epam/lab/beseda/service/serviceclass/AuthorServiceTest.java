package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AuthorDAO;
import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.validator.AuthorValidator;
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
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    private static List<Author> authors;
    private static Author author;

    private static List<AuthorDTO> authorDTOList;
    private static AuthorDTO authorDTO;

    @Mock
    private AuthorDAO authorDao;

    @Mock
    private NewsDAO newsDao;

    @Mock
    private AuthorMapper mapper;

    @Mock
    private NewsMapper newsMapper;

    @Mock
    private AuthorValidator validator;

    @InjectMocks
    private AuthorService service;


    @BeforeClass
    public static void initData() {
        authors = new ArrayList<>();
        authors.add(new Author(1, "Alex", "Boal"));
        authors.add(new Author(2, "Jane", "Dall"));
        authors.add(new Author(3, "Nil", "Furmann"));

        author = new Author("Andrei", "Bogotarev");
        authorDTOList = new ArrayList<>();
        authorDTOList.add(new AuthorDTO(1, "Alex", "Boal"));
        authorDTOList.add(new AuthorDTO(2, "Jane", "Dall"));
        authorDTOList.add(new AuthorDTO(3, "Nil", "Furmann"));

        authorDTO = new AuthorDTO("Andrei", "Bogotarev");
    }


    @Test
    public void testGetAll() {
        Mockito.when(authorDao.getAll()).thenReturn(authors);
        Mockito.when(mapper.toDto(any(Author.class))).thenReturn(new AuthorDTO());

        List<AuthorDTO> receivedAuthors = service.getAll();
        Mockito.verify(authorDao, times(1)).getAll();
        Assert.assertEquals(receivedAuthors.size(),3);
    }


    @Test
    public void testGetEntityById() {
        int id = 1;
        Mockito.when(authorDao.getEntityById(anyInt())).thenReturn(author);
        Mockito.when(mapper.toDto(any(Author.class))).thenReturn(authorDTO);
        AuthorDTO author = service.getDtoById(id);
        Mockito.verify(authorDao, times(1)).getEntityById(id);
        Assert.assertEquals(author, authorDTO);
    }

    @Test
    public void testDelete() {
        int id = 1;
        Mockito.doNothing().when(authorDao).delete(id);
        service.delete(id);
        Mockito.verify(authorDao, times(1)).delete(id);
    }

    @Test
    public void testAdd_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(isA(AuthorDTO.class));
        Mockito.when(authorDao.add(isA(Author.class))).thenReturn(1);
        Mockito.when(mapper.toEntity(any(AuthorDTO.class))).thenReturn(author);

        service.add(authorDTO);
        Mockito.verify(authorDao, times(1)).add(author);
        Mockito.verify(validator, times(1)).validate(authorDTO);
    }

    @Test(expected = ServiceLayerException.class)
    public void testAdd_incorrectData() throws ServiceLayerException, DAOLayerException {
        Mockito.doThrow(new ValidationException()).when(validator).validate(any(AuthorDTO.class));

        service.add(authorDTO);
        Mockito.verify(authorDao, never()).add(author);
        Mockito.verify(validator, times(1)).validate(any(AuthorDTO.class));
    }

    @Test
    public void testUpdate_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.when(authorDao.getEntityById(anyInt())).thenReturn(author);
        Mockito.doNothing().when(validator).validate(any(AuthorDTO.class));
        Mockito.doNothing().when(authorDao).update(any(Author.class));
        Mockito.when(mapper.toEntity(any(AuthorDTO.class))).thenReturn(author);

        service.update(authorDTO);
        Mockito.verify(validator, times(1)).validate(any(AuthorDTO.class));
        Mockito.verify(authorDao, times(1)).update(any(Author.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testUpdate_incorrectData() throws DAOLayerException, ServiceLayerException {
        Mockito.when(authorDao.getEntityById(anyInt())).thenReturn(author);
        Mockito.doThrow(ValidationException.class).when(validator).validate(any(AuthorDTO.class));
        service.update(authorDTO);
        Mockito.verify(validator, times(1)).validate(authorDTO);
        Mockito.verify(authorDao, never()).update(author);
    }

    @Test
    public void testGetNews() {
        int id = 1;
        List<Integer> newsId = new ArrayList<>();
        newsId.add(1);
        newsId.add(2);
        Mockito.when(authorDao.getNewsId(id)).thenReturn(newsId);
        Mockito.when(newsDao.getEntityById(anyInt())).thenReturn(new News());

        List<NewsDTO> newsList = service.getNews(id);
        Mockito.verify(authorDao, atMostOnce()).getNewsId(anyInt());
        Mockito.verify(newsDao, times(2)).getEntityById(anyInt());
    }


}
