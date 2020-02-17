package com.epam.lab.beseda.news_management.dao.mapper;

import com.epam.lab.beseda.news_management.entity.EnumEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.ID;

public class EnumEntityMapper implements RowMapper<EnumEntity> {

    @Override
    public EnumEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        EnumEntity entity=null;
        if(resultSet!=null){
         entity=new EnumEntity();
         entity.setId(resultSet.getInt(ID));
         entity.setValue(resultSet.getString(ENTITY_NAME));
        }
        return entity;
    }
}
