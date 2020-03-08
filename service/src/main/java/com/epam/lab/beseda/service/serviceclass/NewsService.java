package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.*;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.modelmapper.TagMapper;
import com.epam.lab.beseda.service.serviceinterface.NewsServiceInterface;
import com.epam.lab.beseda.service.validator.NewsValidator;
import com.epam.lab.beseda.service.validator.TagValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.lab.beseda.util.FullDataRequiredMessage.NEWS_DATA_REQUIRED;

@Service
public class NewsService extends AbstractService<News, NewsDTO> implements NewsServiceInterface {

    @Autowired
    private AuthorDAOInterface authorDAO;

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

    public NewsService(AuthorDAOInterface authorDAO, NewsDAOInterface newsDAO, TagDAOInterface tagDAO,
                       NewsValidator validator, TagValidator tagValidator, NewsMapper mapper,
                       AuthorMapper authorMapper, TagMapper enumEntityMapper) {
        super(newsDAO, validator, mapper);
        this.authorDAO = authorDAO;
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
        List<String> tagList = ((NewsDAO) dao).getNewsTagsNames(id);
        newsDTO.setTags(new HashSet<>(tagList));
        return newsDTO;
    }

    @Override
    public void add(NewsDTO newsDTO) throws ServiceLayerException {
        validator.validate(newsDTO);
        newsDTO.setCreationDate(LocalDate.now());
        newsDTO.setModificationDate(LocalDate.now());

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
            Tag tag = tagDAO.getEntityByName(tagName);
            if (tag == null) {
                tag = new Tag(tagName);
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
    public NewsDTO update(NewsDTO newsDTO) throws ServiceLayerException {

        News news = dao.getEntityById(newsDTO.getId());
        if (news != null) {
            if (newsDTO.getTitle() == null) {
                newsDTO.setTitle(news.getTitle());
            }
            if (newsDTO.getShortText() == null) {
                newsDTO.setShortText(news.getShortText());
            }
            if (newsDTO.getFullText() == null) {
                newsDTO.setFullText(news.getFullText());
            }
            newsDTO.setModificationDate(LocalDate.now());
            super.update(newsDTO);
            if (newsDTO.getAuthor() != null) {
                Author author = authorMapper.toEntity(newsDTO.getAuthor());
                try {
                    authorDAO.add(author);
                } catch (DAOLayerException e) {
                    throw new ServiceLayerException(e);
                }
                ((NewsDAO) dao).addAuthor(newsDTO.getId(), author.getId());
            }
        } else {
            newsDTO = null;
        }
        return newsDTO;
    }

    @Override
    public void addNewsTags(int newsId, List<String> tags) throws ServiceLayerException {
        List<String> currentTags = ((NewsDAO) dao).getNewsTagsNames(newsId);
        for (String tagName : tags) {
            if (!currentTags.contains(tagName)) {
                Tag tag = tagDAO.getEntityByName(tagName);
                if (tag == null) {
                    tag = new Tag(tagName);
                    try {
                        tagValidator.validate(tagMapper.toDto(tag));
                        tagDAO.add(tag);
                    } catch (DAOLayerException e) {
                        throw new ServiceLayerException(e);
                    }
                }
                ((NewsDAO) dao).addNewsTag(newsId, tag.getId());
            }
        }
    }

    @Override
    public void deleteNewsTags(int newsId, List<String> tags) {
        for (String tagName : tags) {
            Tag tag = tagDAO.getEntityByName(tagName);
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
    public List<TagDTO> getNewsTags(int newsId) {
        List<TagDTO> dtoList = new ArrayList<>();
        for (Tag tag : ((NewsDAO) dao).getNewsTags(newsId)) {
            dtoList.add(tagMapper.toDto(tag));
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
