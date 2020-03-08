package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.exception.DAOLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.Query.*;

@Repository
public class AuthorDAO extends AbstractDAO<Author> implements AuthorDAOInterface {

    public AuthorDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Autowired
    @Override
    protected void setRowMapper(RowMapper<Author> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Autowired
    @Override
    protected void setResultSetExtractor(ResultSetExtractor<Author> rsExtractor) {
        this.rsExtractor = rsExtractor;
    }

    @Override
    protected MapSqlParameterSource createMapSqlParameterSource(Author entity) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(ID, entity.getId());
        sqlParameterSource.addValue(NAME, entity.getName());
        sqlParameterSource.addValue(SURNAME, entity.getSurname());
        return sqlParameterSource;
    }


    @Override
    public int add(Author entity) throws DAOLayerException {
        int id = 0;
        Author existentEntity = this.getAuthorByNameSurname(entity.getName(), entity.getSurname());
        if (existentEntity != null) {
            id = existentEntity.getId();
            entity.setId(id);
        } else {
            id = super.add(entity);
        }
        return id;
    }


    @Override
    public Author getAuthorByNameSurname(String name, String surname) {
        return jdbcTemplate.query(SELECT_AUTHOR_BY_NAME_SURNAME, new Object[]{name, surname}, rsExtractor);
    }

    @Override
    public List<Integer> getNewsId(int authorId) {
        return jdbcTemplate.queryForList(NEWS_AUTHOR_GET_NEWS_ID, new Object[]{authorId}, Integer.class);
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_AUTHORS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_AUTHOR_BY_ID;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_AUTHOR_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEW_AUTHOR;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_AUTHOR;
    }
}
