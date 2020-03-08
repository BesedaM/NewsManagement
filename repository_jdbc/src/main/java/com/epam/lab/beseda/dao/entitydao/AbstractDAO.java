package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.entity.BaseEntity;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DBEntityTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractDAO<E extends BaseEntity> implements AbstractDAOInterface<E> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected RowMapper<E> rowMapper;

    protected ResultSetExtractor<E> rsExtractor;


    protected AbstractDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected AbstractDAO() {
    }

    protected abstract void setRowMapper(RowMapper<E> rowMapper);

    protected abstract void setResultSetExtractor(ResultSetExtractor<E> rsExtractor);

    @Override
    public List<E> getAll() {
        return jdbcTemplate.query(getAllStatement(), rowMapper);
    }

    @Override
    public E getEntityById(int id) {
        return jdbcTemplate.query(getEntityByIdStatement(), new Object[]{id}, rsExtractor);
    }

    @Override
    public int add(E entity) throws DAOLayerException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = createMapSqlParameterSource(entity);
        int rowsNum = namedTemplate.update(addStatement(), parameters, keyHolder, new String[]{DBEntityTable.ID});
        if (rowsNum != 1) {
            throw new DAOLayerException("Error adding "
                    + entity.getClass().getSimpleName().replace(".class", "") + " to database");
        }
        int id = keyHolder.getKey().intValue();
        entity.setId(id);
        return id;
    }

    @Override
    public void update(E entity) throws DAOLayerException {
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = createMapSqlParameterSource(entity);
        npjt.update(updateStatement(), parameters);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(deleteStatement(), id);
    }

    /**
     * Return string representation of SQL 'select all' query.
     */
    protected abstract String getAllStatement();

    /**
     * Return string representation of SQL 'select by id' query.
     */
    protected abstract String getEntityByIdStatement();

    /**
     * Return string representation of SQL 'delete entity' query.
     */
    protected abstract String deleteStatement();

    public void delete(E entity) {
        delete(entity.getId());
    }


    /**
     * Returns string representation of SQL 'add entity service' query.
     */
    protected abstract String addStatement();

    /**
     * Method used in ADD method for inserting values to MapSqlParameterSource
     *
     * @param entity source of values
     * @return configured MapSqlParameterSource
     */
    protected abstract MapSqlParameterSource createMapSqlParameterSource(E entity);

    /**
     * Returns string representation of SQL 'update entity' query.
     */
    protected abstract String updateStatement();


}
