package com.epam.lab.beseda.news_management.dao.resultsetextractor;

import com.epam.lab.beseda.news_management.entity.EnumEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.ID;

public class EnumEntityExtractor implements ResultSetExtractor<EnumEntity> {

    @Override
    public EnumEntity extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        EnumEntity entity=null;
        if(resultSet.next()){
            entity=new EnumEntity();
            entity.setId(resultSet.getInt(ID));
            entity.setValue(resultSet.getString(ENTITY_NAME));
        }
        return entity;
    }
}
