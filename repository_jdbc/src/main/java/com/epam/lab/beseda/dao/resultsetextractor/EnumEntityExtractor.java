package com.epam.lab.beseda.dao.resultsetextractor;

import com.epam.lab.beseda.entity.EnumEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.ID;

@Component
public class EnumEntityExtractor implements ResultSetExtractor<EnumEntity> {

    @Override
    public EnumEntity extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        EnumEntity entity=null;
        if(resultSet.next()){
            entity=new EnumEntity();
            entity.setId(resultSet.getInt(ID));
            entity.setName(resultSet.getString(ENTITY_NAME));
        }
        return entity;
    }
}
