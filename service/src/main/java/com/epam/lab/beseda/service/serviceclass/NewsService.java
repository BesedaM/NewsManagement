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
import com.epam.lab.beseda.exception.ParameterNotExistsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.serviceinterface.NewsServiceInterface;
import com.epam.lab.beseda.service.validator.NewsValidator;
import com.epam.lab.beseda.service.validator.TagValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewsService extends AbstractService<News, NewsDTO> implements NewsServiceInterface {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    @Qualifier("tagDao")
    private TagDAO tagDAO;

    @Autowired
    @Qualifier("enumEntityMapper")
    private EnumEntityMapper enumEntityMapper;

    @Autowired
    @Qualifier("authorMapper")
    private AuthorMapper authorMapper;

    @Autowired
    @Qualifier("tagValidator")
    private TagValidator tagValidator;

    public NewsService() {
    }

    public NewsService(AuthorDAO authorDAO, NewsDAO newsDAO, TagDAO tagDAO, NewsValidator validator, TagValidator tagValidator, NewsMapper mapper,
                       AuthorMapper authorMapper, EnumEntityMapper enumEntityMapper) {
        super(newsDAO, validator, mapper);
        this.authorDAO = authorDAO;
        this.tagDAO = tagDAO;
        this.authorMapper = authorMapper;
        this.enumEntityMapper = enumEntityMapper;
        this.tagValidator = tagValidator;
    }

    @Autowired
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

    @Autowired
    @Override
    @Qualifier("newsMapper")
    protected void setMapper(Mapper<News, NewsDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<NewsDTO> getAllSorted(String param) throws ServiceLayerException {
        List<News> newsList = null;
        try {
            newsList = ((NewsDAO) dao).getAllSorted(param);
        } catch (ParameterNotExistsException e) {
            throw new ServiceLayerException(e);
        }
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (News news :
                newsList) {
            newsDTOList.add(mapper.toDto(news));
        }
        return newsDTOList;
    }

    @Override
    public List<NewsDTO> getAll() {
        List<News> newsList = dao.getAll();
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (News news : newsList) {
            NewsDTO newsDTO = mapper.toDto(news);
            int authorId = ((NewsDAO) dao).getAuthorId(news.getId());
            Author author = authorDAO.getEntityById(authorId);
            newsDTO.setAuthor(authorMapper.toDto(author));
            newsDTOList.add(newsDTO);
            List<String> tags = ((NewsDAO) dao).getNewsTagsNames(news.getId());
            Set<String> tagsSet = new HashSet<>(tags);
            newsDTO.setTags(tagsSet);
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO getDtoById(int id) {
        News news = dao.getEntityById(id);
        NewsDTO newsDTO = mapper.toDto(news);
        int authorId = ((NewsDAO) dao).getAuthorId(id);
        Author author = authorDAO.getEntityById(authorId);
        AuthorDTO authorDTO = authorMapper.toDto(author);
        newsDTO.setAuthor(authorDTO);
        return newsDTO;
    }

    @Override
    public void add(NewsDTO newsDTO) throws ServiceLayerException {
        super.add(newsDTO);
        Author author = authorMapper.toEntity(newsDTO.getAuthor());
        try {
            authorDAO.add(author);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        ((NewsDAO) dao).addAuthor(newsDTO.getId(), author.getId());

        Set<String> tagsNames = newsDTO.getTags();
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
            ((NewsDAO) dao).addNewsTag(newsDTO.getId(), tag.getId());
        }
    }


    @Override
    public void update(NewsDTO newsDTO) throws ServiceLayerException {
        super.update(newsDTO);
        Author author = authorMapper.toEntity(newsDTO.getAuthor());
        try {
            authorDAO.add(author);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        ((NewsDAO) dao).addAuthor(newsDTO.getId(), author.getId());
    }

    @Override
    public void addNewsTags(int newsId, List<String> tags) throws ServiceLayerException {
        for (String tagName : tags) {
            EnumEntity tag = tagDAO.getEntityByName(tagName);
            if (tag == null) {
                tag = new EnumEntity(tagName);
                try {
                    tagValidator.validate(enumEntityMapper.toDto(tag));
                    tagDAO.add(tag);
                } catch (DAOLayerException e) {
                    throw new ServiceLayerException(e);
                }
            }
            ((NewsDAO) dao).addNewsTag(newsId, tag.getId());
        }
    }

    @Override
    public void deleteNewsTags(int newsId, List<String> tags) {
        for (String tagName : tags) {
            EnumEntity tag = tagDAO.getEntityByName(tagName);
            if (tag != null) {
                ((NewsDAO) dao).deleteNewsTag(newsId, tag.getId());
            }
        }
    }


    @Override
    public int getNewsNumber() {
        return ((NewsDAO) dao).getNewsNumber();
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
