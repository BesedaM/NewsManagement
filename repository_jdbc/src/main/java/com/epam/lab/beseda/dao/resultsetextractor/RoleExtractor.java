package com.epam.lab.beseda.dao.resultsetextractor;

import com.epam.lab.beseda.entity.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.ID;

@Component
public class RoleExtractor implements ResultSetExtractor<Role> {

    @Override
    public Role extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Role entity = null;
        if (resultSet.next()) {
            entity = new Role();
            entity.setId(resultSet.getInt(ID));
            entity.setName(resultSet.getString(ENTITY_NAME));
        }
        return entity;
    }
}
