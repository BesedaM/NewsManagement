package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.ParameterNotExistsException;
import com.epam.lab.beseda.util.DBConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.epam.lab.beseda.util.DBConstants.*;
import static com.epam.lab.beseda.util.JpaParametersName.*;
import static com.epam.lab.beseda.util.Query.*;

@Transactional
@Repository
public class NewsDAO extends AbstractDAO<News> implements NewsDAOInterface {

    @Autowired
    private AuthorDAOInterface authorDAO;

    @Autowired
    private TagDAOInterface tagDAO;

    public NewsDAO() {
    }

    public NewsDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Autowired
    protected void setClass() {
        this.className = News.class;
    }

    @Override
    public int add(News entity) {
        Author author = entity.getAuthor();
        authorDAO.add(author);
        Set<Tag> tags = entity.getTags();
        if (tags.size() > 0) {
            for (Tag tag : tags) {
                tagDAO.add(tag);
            }
        }
        entityManager.persist(entity);
        return entity.getId();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getNewsByAuthor(Author author) {
        return entityManager.createQuery(SEARCH_NEWS_BY_AUTHOR).setParameter(JPA_NAME, author.getName()).setParameter(JPA_SURNAME, author.getSurname()).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> findByTagsList(List<String> tagsNames) {
        Set<Tag> tags = new TreeSet<>();
        for (int i = 0; i < tagsNames.size(); i++) {
            Tag tag = tagDAO.getEntityByName(tagsNames.get(i));
            if (tag != null) {
                tags.add(tag);
            }
        }
        return entityManager.createQuery(SEARCH_NEWS_BY_TAG).setParameter(JPA_SEARCH_LIST, tags).getResultList();
    }

    @Override
    public List<News> getAllSorted(String param) throws ParameterNotExistsException {
        String query = null;
        switch (param) {
            case AUTHOR: {
                query = NEWS_ORDER_BY_AUTHOR;
                break;
            }
            case DATE: {
                query = NEWS_ORDER_BY_CREATION_DATE;
                break;
            }
            case DBConstants.TAG: {
                query = getAllStatement();
                break;
            }
            default:
                throw new ParameterNotExistsException(SORTING_PARAMETER_REQUIREMENTS);
        }
        return entityManager.createQuery(query, className).getResultList();
    }

    @Override
    public int getNewsNumber() {
        return entityManager.createQuery(COUNT_NEWS_NUMBER, Long.class).getSingleResult().intValue();
    }

    @Override
    public void addNewsTag(News news, Tag tag) {
        if (news != null && tag != null) {
            tagDAO.add(tag);
            Set<Tag> tags = news.getTags();
            tags.add(tag);
            entityManager.merge(news);
        }
    }

    @Override
    public void deleteNewsTag(int newsId, int tagId) {
        entityManager.createNativeQuery(NEWS_TAG_DELETE_TAG).setParameter(1, newsId).setParameter(2, tagId).executeUpdate();
    }

    @Override
    public void delete(int id) {
        entityManager.createNativeQuery(NEWS_AUTHOR_DELETE_DEPENDENCY).setParameter(1, id).executeUpdate();
        entityManager.createNativeQuery(NEWS_TAG_DELETE_ALL_TAGS).setParameter(1, id).executeUpdate();
        entityManager.createNativeQuery(DELETE_NEWS_BY_ID).setParameter(1, id).executeUpdate();
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_NEWS;
    }
}
