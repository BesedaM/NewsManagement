package com.epam.lab.beseda.dao.resultsetextractor;

import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.entity.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.ID;

@Component
public class TagExtractor implements ResultSetExtractor<Tag> {

    @Override
    public Tag extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Tag entity = null;
        if (resultSet.next()) {
            entity = new Tag();
            entity.setId(resultSet.getInt(ID));
            entity.setName(resultSet.getString(ENTITY_NAME));
        }
        return entity;
    }
}
