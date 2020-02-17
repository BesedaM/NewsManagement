package com.epam.lab.beseda.news_management.dao.mapper;

import com.epam.lab.beseda.news_management.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.*;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet result, int rowNum) throws SQLException {
        User user = null;
        if (result != null) {
            user = new User();
            user.setId(result.getInt(ID));
            user.setName(result.getString(NAME));
            user.setSurname(result.getString(SURNAME));
            user.setLogin(result.getString(LOGIN));
            user.setPassword(result.getString(PASSWORD));
        }
        return user;
    }

}

