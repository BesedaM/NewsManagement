package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.ParameterNotExistsException;
import com.epam.lab.beseda.util.DBConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import static com.epam.lab.beseda.util.DBConstants.*;
import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.Query.*;


@Repository
public class NewsDAO extends AbstractDAO<News> implements NewsDAOInterface {
    public NewsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Autowired
    @Qualifier("tagRowMapper")
    private RowMapper<Tag> tagRowMapper;

    @Autowired
    @Qualifier("newsRowMapper")
    @Override
    protected void setRowMapper(RowMapper<News> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Autowired
    @Qualifier("newsExtractor")
    @Override
    protected void setResultSetExtractor(ResultSetExtractor<News> rsExtractor) {
        this.rsExtractor = rsExtractor;
    }

    @Override
    protected MapSqlParameterSource createMapSqlParameterSource(News entity) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(ID, entity.getId());
        sqlParameterSource.addValue(TITLE, entity.getTitle());
        sqlParameterSource.addValue(SHORT_TEXT, entity.getShortText());
        sqlParameterSource.addValue(FULL_TEXT, entity.getFullText());
        sqlParameterSource.addValue(CREATION_DATE, Date.valueOf(entity.getCreationDate()));
        sqlParameterSource.addValue(MODIFICATION_DATE, Date.valueOf(entity.getModificationDate()));
        return sqlParameterSource;
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
                query = NEWS_ORDER_BY_TAG;
                break;
            }
            default:
                throw new ParameterNotExistsException(SORTING_PARAMETER_REQUIREMENTS);
        }
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public List<News> getNewsByAuthor(Author author) {
        return jdbcTemplate.query(SEARCH_NEWS_BY_AUTHOR, new Object[]{author.getName(), author.getSurname()}, rowMapper);
    }

    @Override
    public int getNewsNumber() {
        return jdbcTemplate.queryForObject(COUNT_NEWS_NUMBER, Integer.class);
    }

    @Override
    public void addNewsTag(int newsId, int tagId) {
        jdbcTemplate.update(NEWS_TAG_ADD_TAG, newsId, tagId);
    }

    @Override
    public void deleteNewsTag(int newsId, int tagId) {
        jdbcTemplate.update(NEWS_TAG_DELETE_TAG, newsId, tagId);
    }

    @Override
    public List<String> getNewsTagsNames(int newsId) {
        return jdbcTemplate.queryForList(NEWS_TAG_GET_TAGS_NAMES, new Object[]{newsId}, String.class);
    }

    @Override
    public List<Tag> getNewsTags(int newsId) {
        return jdbcTemplate.query(NEWS_TAG_GET_TAGS, new Object[]{newsId}, tagRowMapper);
    }

    @Override
    public void addAuthor(int newsId, int authorId) {
        jdbcTemplate.update(NEWS_AUTHOR_ADD_AUTHOR, newsId, authorId);
    }

    @Override
    public int getAuthorId(int newsId) {
        return jdbcTemplate.queryForObject(NEWS_AUTHOR_GET_AUTHOR_ID, new Object[]{newsId}, Integer.class);
    }

    @Override
    public List<Integer> getNewsId(int tagId) {
        return jdbcTemplate.queryForList(NEWS_TAG_GET_NEWS, new Object[]{tagId}, Integer.class);
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_NEWS_WITH_AUTHORS_TAGS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_NEWS_BY_ID;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_NEWS_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEWS;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_NEWS;
    }
}
