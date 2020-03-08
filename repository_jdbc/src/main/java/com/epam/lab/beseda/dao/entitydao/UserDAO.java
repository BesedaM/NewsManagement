package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.UserDAOInterface;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.Query.*;

@Repository
public class UserDAO extends AbstractDAO<User> implements UserDAOInterface {

    public UserDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Autowired
    @Qualifier("roleExtractor")
    protected ResultSetExtractor<Role> roleExtractor;

    @Autowired
    @Qualifier("userRowMapper")
    @Override
    protected void setRowMapper(RowMapper<User> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Autowired
    @Qualifier("userExtractor")
    @Override
    protected void setResultSetExtractor(ResultSetExtractor<User> rsExtractor) {
        this.rsExtractor = rsExtractor;
    }

    @Override
    protected MapSqlParameterSource createMapSqlParameterSource(User entity) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(ID, entity.getId());
        sqlParameterSource.addValue(NAME, entity.getName());
        sqlParameterSource.addValue(SURNAME, entity.getSurname());
        sqlParameterSource.addValue(LOGIN, entity.getLogin());
        sqlParameterSource.addValue(PASSWORD, entity.getPassword());
        return sqlParameterSource;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return jdbcTemplate.query(SELECT_USER_BY_LOGIN_AND_PASSWORD, new Object[]{login, password}, rsExtractor);
    }

    @Override
    public User getUserByLogin(String login) {
        return jdbcTemplate.query(SELECT_USER_BY_LOGIN, new Object[]{login}, rsExtractor);
    }

    @Override
    public void setRole(int userId, int roleId) {
        jdbcTemplate.update(USER_ADD_ROLE, roleId, userId);
    }

    @Override
    public Role getRole(int userId) {
        return jdbcTemplate.query(USER_ROLE_GET_ROLE, new Object[]{userId}, roleExtractor);
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_USERS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_USER_BY_ID;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_USER_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEW_USER;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_USER;
    }
}
