package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.EnumEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.ENTITY_NAME;
import static com.epam.lab.beseda.util.DBEntityTable.ID;

@Component
public class EnumEntityRowMapper implements RowMapper<EnumEntity> {

    @Override
    public EnumEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        EnumEntity entity=null;
        if(resultSet!=null){
         entity=new EnumEntity();
         entity.setId(resultSet.getInt(ID));
         entity.setName(resultSet.getString(ENTITY_NAME));
        }
        return entity;
    }
}
