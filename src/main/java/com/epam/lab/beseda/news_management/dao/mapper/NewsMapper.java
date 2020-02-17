package com.epam.lab.beseda.news_management.dao.mapper;

import com.epam.lab.beseda.news_management.entity.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import static com.epam.lab.beseda.news_management.dao.util.DBEntityTable.*;


public class NewsMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet resultSet, int i) throws SQLException {
        News news = null;
        if (resultSet != null) {
            news=new News();
            news.setId(resultSet.getInt(ID));
            news.setTitle(resultSet.getString(TITLE));
            news.setShortText(resultSet.getString(SHORT_TEXT));
            news.setFullText(resultSet.getString(FULL_TEXT));
            GregorianCalendar creationDate = new GregorianCalendar();
            creationDate.setTime(resultSet.getDate(CREATION_DATE));
            news.setCreationDate(creationDate);
            GregorianCalendar modificationDate = new GregorianCalendar();
            creationDate.setTime(resultSet.getDate(MODIFICATION_DATE));
            news.setModificationDate(modificationDate);
        }
        return news;
    }
}
