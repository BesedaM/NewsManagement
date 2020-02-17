package com.epam.lab.beseda.news_management.dao.resultsetextractor;

import com.epam.lab.beseda.news_management.entity.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.*;

public class AuthorExtractor implements ResultSetExtractor<Author> {

    @Override
    public Author extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Author author=null;
        if(resultSet.next()){
            author=new Author();
            author.setId(resultSet.getInt(ID));
            author.setName(resultSet.getString(NAME));
            author.setSurname(resultSet.getString(SURNAME));
        }
        return author;
    }
}
