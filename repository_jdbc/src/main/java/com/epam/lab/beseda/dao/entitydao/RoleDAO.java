package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.RoleDAOInterface;
import com.epam.lab.beseda.entity.Role;
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
import static com.epam.lab.beseda.util.Query.SELECT_ENTITY_BY_NAME;

@Repository("roleDao")
public class RoleDAO extends AbstractDAO<Role> implements RoleDAOInterface {

    private String tableName = T_ROLES;

    public RoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    @Qualifier("roleRowMapper")
    @Override
    protected void setRowMapper(RowMapper<Role> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Autowired
    @Qualifier("roleExtractor")
    @Override
    protected void setResultSetExtractor(ResultSetExtractor<Role> rsExtractor) {
        this.rsExtractor = rsExtractor;
    }

    @Override
    protected MapSqlParameterSource createMapSqlParameterSource(Role entity) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(ID, entity.getId());
        sqlParameterSource.addValue(ENTITY_NAME, entity.getName());
        return sqlParameterSource;
    }

    @Override
    public int add(Role entity) throws DAOLayerException {
        int id = 0;
        Role existentEntity = this.getEntityByName(entity.getName());
        if (existentEntity != null) {
            id = existentEntity.getId();
            entity.setId(id);
        } else {
            id = super.add(entity);
        }
        return id;
    }

    @Override
    public Role getEntityByName(String name) {
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
