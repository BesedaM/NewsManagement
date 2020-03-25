package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.dto.comparator.NewsTagsComparator;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.ParameterNotExistsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.modelmapper.TagMapper;
import com.epam.lab.beseda.service.serviceinterface.NewsServiceInterface;
import com.epam.lab.beseda.service.validator.NewsValidator;
import com.epam.lab.beseda.service.validator.TagValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import com.epam.lab.beseda.util.DBConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class NewsService extends AbstractService<News, NewsDTO> implements NewsServiceInterface {

    @Autowired
    private TagDAOInterface tagDAO;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    @Qualifier("authorMapper")
    private AuthorMapper authorMapper;

    @Autowired
    @Qualifier("tagValidator")
    private TagValidator tagValidator;

    public NewsService() {
    }

    public NewsService(NewsDAOInterface newsDAO, TagDAOInterface tagDAO,
                       NewsValidator validator, TagValidator tagValidator, NewsMapper mapper,
                       AuthorMapper authorMapper, TagMapper enumEntityMapper) {
        super(newsDAO, validator, mapper);
        this.tagDAO = tagDAO;
        this.authorMapper = authorMapper;
        this.tagMapper = enumEntityMapper;
        this.tagValidator = tagValidator;
    }

    @Autowired
    @Override
    protected void setDao(AbstractDAOInterface<News> dao) {
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
            newsList = ((NewsDAOInterface) dao).getAllSorted(param);
        } catch (ParameterNotExistsException e) {
            throw new ServiceLayerException(e);
        }
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (News news : newsList) {
            newsDTOList.add(mapper.toDto(news));
        }
        if(param.equals(DBConstants.TAG)){
            newsDTOList.sort(new NewsTagsComparator());
        }

        return newsDTOList;
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) throws ServiceLayerException {
        News news = dao.getEntityById(newsDTO.getId());
        if (news != null) {
            if (newsDTO.getTitle() == null) {
                newsDTO.setTitle(news.getTitle());
            }
            if (newsDTO.getShort_text() == null) {
                newsDTO.setShort_text(news.getShort_text());
            }
            if (newsDTO.getFull_text() == null) {
                newsDTO.setFull_text(news.getFull_text());
            }
            if (newsDTO.getAuthor() == null) {
                AuthorDTO authorDTO = authorMapper.toDto(news.getAuthor());
                newsDTO.setAuthor(authorDTO);
            }
            newsDTO.setModification_date(LocalDate.now());
            super.update(newsDTO);
        } else {
            newsDTO = null;
        }
        return newsDTO;
    }

    @Override
    public void addNewsTags(int newsId, List<String> tags) throws ValidationException {
        News news = dao.getEntityById(newsId);
        Set<Tag> oldTags = news.getTags();
        for (String tagName : tags) {
            Tag newTag = new Tag(tagName);
            tagValidator.validate(new TagDTO(tagName));
            tagDAO.add(newTag);
            oldTags.add(newTag);
        }
        dao.update(news);

    }

    @Override
    public void deleteNewsTags(int newsId, List<String> tags) {
        News news = dao.getEntityById(newsId);
        Set<Tag> oldTags = news.getTags();
        for (String tagName : tags) {
            Tag tag = new Tag(tagName);
            oldTags.remove(tag);
        }
        dao.update(news);
    }


    @Override
    public int getNewsNumber() {
        return ((NewsDAOInterface) dao).getNewsNumber();
    }

    @Override
    public List<TagDTO> getNewsTags(int newsId) {
        List<TagDTO> dtoList = new ArrayList<>();
        News news = dao.getEntityById(newsId);
        for (Tag tag : news.getTags()) {
            dtoList.add(tagMapper.toDto(tag));
        }
        return dtoList;
    }


}
