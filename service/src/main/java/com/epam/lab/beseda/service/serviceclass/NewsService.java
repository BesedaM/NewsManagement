package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AbstractDAO;
import com.epam.lab.beseda.dao.entitydao.AuthorDAO;
import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dao.entitydao.TagDAO;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.serviceinterface.NewsServiceInterface;
import com.epam.lab.beseda.service.validator.NewsValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService extends AbstractService<News, NewsDTO> implements NewsServiceInterface {

    @Autowired
//    @Qualifier("authorDAO")
    private AuthorDAO authorDAO;

    @Autowired
//    @Qualifier("tagDAO")
    private TagDAO tagDAO;

    @Autowired
    @Qualifier("enumEntityMapper")
    private EnumEntityMapper enumEntityMapper;

    @Autowired
    @Qualifier("authorMapper")
    private AuthorMapper authorMapper;

    public NewsService() {
    }

    public NewsService(AuthorDAO authorDAO, NewsDAO newsDAO, TagDAO tagDAO, NewsValidator validator, NewsMapper mapper,
                       AuthorMapper authorMapper, EnumEntityMapper enumEntityMapper) {
        super(newsDAO, validator, mapper);
        this.authorDAO = authorDAO;
        this.tagDAO = tagDAO;
        this.authorMapper = authorMapper;
        this.enumEntityMapper = enumEntityMapper;
    }

    @Autowired
//    @Qualifier("newsDAO")
    @Override
    protected void setDao(AbstractDAO<News> dao) {
        this.dao = dao;
    }

    @Autowired
    @Qualifier("newsValidator")
    @Override
    protected void setValidator(Validatable<NewsDTO> validator) {
        this.validator = validator;
    }

    @Override
    @Qualifier("newsMapper")
    protected void setMapper(Mapper<News, NewsDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<NewsDTO> getAll() {
        List<News> newsList = dao.getAll();
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (News news : newsList) {
            int authorId = ((NewsDAO) dao).getAuthorId(news.getId());
            Author author = authorDAO.getEntityById(authorId);
            news.setAuthor(author);
            newsDTOList.add(mapper.toDto(news));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO getDtoById(int id) {
        News news = dao.getEntityById(id);
        int authorId = ((NewsDAO) dao).getAuthorId(id);
        Author author = authorDAO.getEntityById(authorId);
        news.setAuthor(author);
        return mapper.toDto(news);
    }

    @Override
    public void add(NewsDTO dto) throws ServiceLayerException {
        super.add(dto);
        Author author = new Author(dto.getAuthorName(), dto.getAuthorSurname());
        try {
            authorDAO.add(author);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        ((NewsDAO) dao).addAuthor(dto.getId(), author.getId());

        List<String> tagsNames = dto.getTags();
        for (String tagName : tagsNames) {
            EnumEntity tag = tagDAO.getEntityByName(tagName);
            if (tag == null) {
                tag = new EnumEntity(tagName);
                try {
                    tagDAO.add(tag);
                } catch (DAOLayerException e) {
                    throw new ServiceLayerException(e);
                }
            }
            ((NewsDAO) dao).addAuthor(dto.getId(), tag.getId());
        }
    }


    @Override
    public void update(NewsDTO dto) throws ServiceLayerException {
        super.update(dto);
        Author author = new Author(dto.getAuthorName(), dto.getAuthorSurname());
        try {
            authorDAO.add(author);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        ((NewsDAO) dao).addAuthor(dto.getId(), author.getId());

        List<String> tagsNames = dto.getTags();
        List<EnumEntity> tags = new ArrayList<>();
        for (String tagName : tagsNames) {
            EnumEntity tag = tagDAO.getEntityByName(tagName);
            if (tag == null) {
                tag = new EnumEntity(tagName);
                try {
                    tagDAO.add(tag);
                } catch (DAOLayerException e) {
                    throw new ServiceLayerException(e);
                }
            }
            ((NewsDAO) dao).addAuthor(dto.getId(), tag.getId());
        }
    }


    @Override
    public int getNewsNumber() {
        return ((NewsDAO) dao).getNewsNumber();
    }


    @Override
    public void addNewsTag(int newsId, int tagId) {
        ((NewsDAO) dao).addNewsTag(newsId, tagId);
    }


    @Override
    public void deleteNewsTag(int newsId, int tagId) {
        ((NewsDAO) dao).deleteNewsTag(newsId, tagId);
    }


    @Override
    public List<String> getNewsTagsNames(int newsId) {
        return ((NewsDAO) dao).getNewsTagsNames(newsId);
    }


    @Override
    public List<EnumEntityDTO> getNewsTags(int newsId) {
        List<EnumEntityDTO> dtoList = new ArrayList<>();
        for (EnumEntity tag : ((NewsDAO) dao).getNewsTags(newsId)) {
            dtoList.add(enumEntityMapper.toDto(tag));
        }
        return dtoList;
    }


    @Override
    public void addAuthor(int newsId, int authorId) {
        ((NewsDAO) dao).addAuthor(newsId, authorId);
    }


    @Override
    public AuthorDTO getAuthor(int newsId) {
        int authorId = ((NewsDAO) dao).getAuthorId(newsId);
        Author author = authorDAO.getEntityById(authorId);
        AuthorDTO authorDTO = authorMapper.toDto(author);

        List<Integer> newsIdList = authorDAO.getNewsId(authorId);
        List<NewsDTO> newsDtoList = new ArrayList<>();
        for (Integer id : newsIdList) {
            News news = dao.getEntityById(id);
            newsDtoList.add(mapper.toDto(news));
        }
        authorDTO.setNewsList(newsDtoList);
        return authorDTO;
    }


    @Override
    public List<NewsDTO> getNewsByTagId(int tagId) {
        List<Integer> newsId = ((NewsDAO) dao).getNewsId(tagId);
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (Integer id : newsId) {
            News news = dao.getEntityById(id);
            int authorId = ((NewsDAO) dao).getAuthorId(id);
            Author author = authorDAO.getEntityById(authorId);
            news.setAuthor(author);
            NewsDTO newsDTO = mapper.toDto(news);

            newsDTOList.add(newsDTO);
        }
        return newsDTOList;
    }

}
