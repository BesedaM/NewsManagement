package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.ID;

@Component
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role entity = new Role();
        entity.setId(resultSet.getInt(ID));
        entity.setName(resultSet.getString(ENTITY_NAME));
        return entity;
    }
}

