package com.epam.lab.beseda.dao.resultsetextractor;

import com.epam.lab.beseda.entity.News;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.DBEntityTable.MODIFICATION_DATE;

@Component
public class NewsExtractor implements ResultSetExtractor<News> {

    @Override
    public News extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        News news = null;
        if (resultSet.next()) {
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
