package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AbstractDAO;
import com.epam.lab.beseda.dao.entitydao.AuthorDAO;
import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.serviceinterface.AuthorServiceInterface;
import com.epam.lab.beseda.service.validator.AuthorValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService extends AbstractService<Author, AuthorDTO> implements AuthorServiceInterface {

    public AuthorService() {
        super();
    }

    public AuthorService(AuthorDAO authorDAO, NewsDAO newsDao, AuthorValidator validator, AuthorMapper mapper, NewsMapper newsMapper) {
        super(authorDAO, validator, mapper);
        this.newsDao = newsDao;
        this.newsMapper = newsMapper;
    }

    @Autowired
//    @Qualifier("newsDAO")
    private NewsDAO newsDao;

    @Autowired
    @Qualifier("newsMapper")
    private NewsMapper newsMapper;

    @Autowired
//    @Qualifier("authorDAO")
    @Override
    protected void setDao(AbstractDAO<Author> dao) {
        this.dao = dao;
    }

    @Autowired
    @Qualifier("authorValidator")
    @Override
    protected void setValidator(Validatable<AuthorDTO> validator) {
        this.validator = validator;
    }


    @Autowired
    @Qualifier("authorMapper")
    @Override
    protected void setMapper(Mapper<Author, AuthorDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AuthorDTO> getAll() {
        List<AuthorDTO> authorDtoList = super.getAll();
        for (AuthorDTO dto : authorDtoList) {
            dto.setNewsList(this.getNews(dto.getId()));
        }
        return authorDtoList;
    }

    @Override
    public AuthorDTO getDtoById(int id) {
        AuthorDTO authorDTO = super.getDtoById(id);
        authorDTO.setNewsList(this.getNews(id));
        return authorDTO;
    }

    public List<NewsDTO> getNews(int authorId) {
        List<Integer> newsId = ((AuthorDAO) dao).getNewsId(authorId);
        List<NewsDTO> newsDtoList = new ArrayList<>();
        for (Integer id : newsId) {
            News news = newsDao.getEntityById(id);
            newsDtoList.add(newsMapper.toDto(news));
        }
        return newsDtoList;
    }

}
