package com.epam.lab.beseda.dao.resultsetextractor;

import com.epam.lab.beseda.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.*;

@Component
public class UserExtractor implements ResultSetExtractor<User> {

    @Override
    public User extractData(ResultSet result) throws SQLException, DataAccessException {
        User user = null;
        if (result.next()) {
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
