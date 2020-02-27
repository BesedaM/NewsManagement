package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.*;

@Component
public class UserRowMapper implements RowMapper<User> {

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

