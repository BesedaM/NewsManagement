package com.epam.lab.beseda.news_management.dao.entitydao;

import com.epam.lab.beseda.news_management.dao.daointeface.EnumEntityDAOInterface;
import com.epam.lab.beseda.news_management.entity.EnumEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.epam.lab.beseda.news_management.dao.util.DBConstants.TABLE_NAME;
import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.ID;
import static com.epam.lab.beseda.news_management.dao.util.Query.*;

@Repository
public abstract class EnumEntityDAO extends AbstractDAO<EnumEntity> implements EnumEntityDAOInterface {

    protected String tableName;

    {
        this.setTableName();
    }

    protected EnumEntityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    @Qualifier("enumEntityMapper")
    @Override
    protected void setRowMapper(RowMapper<EnumEntity> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Autowired
    @Qualifier("enumEntityExtractor")
    @Override
    protected void setResultSetExtractor(ResultSetExtractor<EnumEntity> rsExtractor) {
        this.rsExtractor = rsExtractor;
    }

    @Override
    protected MapSqlParameterSource createMapSqlParameterSource(EnumEntity entity) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(ID, entity.getId());
        sqlParameterSource.addValue(ENTITY_NAME, entity.getValue());
        return sqlParameterSource;
    }

    protected abstract void setTableName();

    @Override
    public EnumEntity getEntityByName(String name) {
        return jdbcTemplate.query(getEntityByNameStatement(), new Object[] { name }, rsExtractor);
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

    private String getEntityByNameStatement(){ return SELECT_ENTITY_BY_NAME.replace(TABLE_NAME,tableName);}


}
