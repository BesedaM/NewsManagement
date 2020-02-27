package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.*;

@Component
public class AuthorRowMapper implements RowMapper<Author> {

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
