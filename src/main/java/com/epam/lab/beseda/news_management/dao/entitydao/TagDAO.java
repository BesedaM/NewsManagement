package com.epam.lab.beseda.news_management.dao.entitydao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.T_TAG;

@Repository
public class TagDAO extends EnumEntityDAO{

    public TagDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected void setTableName() {
        this.tableName =T_TAG;
    }
}
