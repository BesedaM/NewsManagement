package com.epam.lab.beseda.news_management.dao.mapper;

import com.epam.lab.beseda.news_management.entity.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.*;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author=null;
        if(resultSet!=null){
            author=new Author();
            author.setId(resultSet.getInt(ID));
            author.setName(resultSet.getString(NAME));
            author.setSurname(resultSet.getString(SURNAME));
        }
        return author;
    }
}
