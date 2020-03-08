package com.epam.lab.beseda.dao.rowmapper;

import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.epam.lab.beseda.util.DBConstants.SPACE_SYMBOL;
import static com.epam.lab.beseda.util.DBConstants.TAGS_LIST;
import static com.epam.lab.beseda.util.DBEntityTable.*;

@Component
public class NewsRowMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet resultSet, int i) throws SQLException {
        News news = new News();
        news.setId(resultSet.getInt(ID));
        news.setTitle(resultSet.getString(TITLE));
        news.setShortText(resultSet.getString(SHORT_TEXT));
        news.setFullText(resultSet.getString(FULL_TEXT));
        news.setCreationDate(resultSet.getDate(CREATION_DATE).toLocalDate());
        news.setModificationDate(resultSet.getDate(MODIFICATION_DATE).toLocalDate());
        Author author = new Author();
        author.setId(resultSet.getInt(AUTHOR_ID));
        author.setName(resultSet.getString(NAME));
        author.setSurname(resultSet.getString(SURNAME));
        news.setAuthor(author);
        String taglist = resultSet.getString(TAGS_LIST);
        if (taglist != null) {
            String[] tagArr = taglist.split(SPACE_SYMBOL);
            Set<String> tagSet = new HashSet<>(Arrays.asList(tagArr));
            news.setTags(tagSet);
        }
        return news;
    }
}
