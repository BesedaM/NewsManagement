package com.epam.lab.beseda.dao.entitydao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.epam.lab.beseda.util.DBEntityTable.T_ROLES;

@Repository("roleDao")
public class RoleDAO extends EnumEntityDAO{

    public RoleDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected void setTableName() {
       this.tableName =T_ROLES;
    }
}
