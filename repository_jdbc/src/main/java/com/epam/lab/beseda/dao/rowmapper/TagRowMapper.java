package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.ID;

@Component
public class TagRowMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag entity = new Tag();
        entity.setId(resultSet.getInt(ID));
        entity.setName(resultSet.getString(ENTITY_NAME));
        return entity;
    }
}
