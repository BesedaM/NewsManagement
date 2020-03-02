package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.News;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.lab.beseda.util.DBEntityTable.*;

@Component
public class NewsRowMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet resultSet, int i) throws SQLException {
        News news = null;
        if (resultSet != null) {
            news=new News();
            news.setId(resultSet.getInt(ID));
            news.setTitle(resultSet.getString(TITLE));
            news.setShortText(resultSet.getString(SHORT_TEXT));
            news.setFullText(resultSet.getString(FULL_TEXT));
            news.setCreationDate(resultSet.getDate(CREATION_DATE).toLocalDate());
            news.setModificationDate(resultSet.getDate(MODIFICATION_DATE).toLocalDate());
        }
        return news;
    }
}
