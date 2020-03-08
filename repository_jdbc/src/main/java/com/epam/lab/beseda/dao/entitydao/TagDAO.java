package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.DAOLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.epam.lab.beseda.util.DBConstants.TABLE_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.Query.*;

@Repository("tagDao")
public class TagDAO extends AbstractDAO<Tag> implements TagDAOInterface {

    private String tableName = T_TAG;

    public TagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    @Qualifier("tagRowMapper")
    @Override
    protected void setRowMapper(RowMapper<Tag> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Autowired
    @Qualifier("tagExtractor")
    @Override
    protected void setResultSetExtractor(ResultSetExtractor<Tag> rsExtractor) {
        this.rsExtractor = rsExtractor;
    }

    @Override
    protected MapSqlParameterSource createMapSqlParameterSource(Tag entity) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(ID, entity.getId());
        sqlParameterSource.addValue(ENTITY_NAME, entity.getName());
        return sqlParameterSource;
    }

    @Override
    public int add(Tag entity) throws DAOLayerException {
        int id = 0;
        Tag existentEntity = this.getEntityByName(entity.getName());
        if (existentEntity != null) {
            id = existentEntity.getId();
            entity.setId(id);
        } else {
            id = super.add(entity);
        }
        return id;
    }

    @Override
    public Tag getEntityByName(String name) {
        return jdbcTemplate.query(getEntityByNameStatement(), new Object[]{name}, rsExtractor);
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_ELEMENTS.replace(TABLE_NAME, tableName);
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_ELEMENT_BY_ID.replace(TABLE_NAME, tableName);
    }

    @Override
    protected String deleteStatement() {
        return DELETE_ELEMENT_BY_ID.replace(TABLE_NAME, tableName);
    }

    @Override
    protected String addStatement() {
        return ADD_ELEMENT.replace(TABLE_NAME, tableName);
    }

    @Override
    protected String updateStatement() {
        return UPDATE_ELEMENT.replace(TABLE_NAME, tableName);
    }

    private String getEntityByNameStatement() {
        return SELECT_ENTITY_BY_NAME.replace(TABLE_NAME, tableName);
    }
}
