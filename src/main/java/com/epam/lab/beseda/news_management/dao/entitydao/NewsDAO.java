package com.epam.lab.beseda.news_management.dao.entitydao;

import com.epam.lab.beseda.news_management.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.news_management.entity.EnumEntity;
import com.epam.lab.beseda.news_management.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.*;
import static com.epam.lab.beseda.news_management.dao.util.Query.*;

@Repository
public class NewsDAO extends AbstractDAO<News> implements NewsDAOInterface {

    public NewsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Autowired
    @Qualifier("enumEntityMapper")
    private RowMapper<EnumEntity> enumEntityRowMapper;

    @Autowired
    @Qualifier("newsMapper")
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
        sqlParameterSource.addValue(CREATION_DATE, entity.getCreationDate());
        sqlParameterSource.addValue(MODIFICATION_DATE, entity.getModificationDate());
        return sqlParameterSource;
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
    public List<EnumEntity> getNewsTags(int newsId) {
        return jdbcTemplate.query(NEWS_TAG_GET_TAGS, new Object[]{newsId}, enumEntityRowMapper);
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
        return SELECT_ALL_NEWS;
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
