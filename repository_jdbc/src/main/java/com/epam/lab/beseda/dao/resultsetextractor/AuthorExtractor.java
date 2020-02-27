package com.epam.lab.beseda.dao.resultsetextractor;

import com.epam.lab.beseda.entity.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.*;

@Component
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
