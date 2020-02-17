package com.epam.lab.beseda.news_management.config;

import com.epam.lab.beseda.news_management.dao.entitydao.*;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.epam.lab.beseda.news_management.dao.util.DatabaseProperties.*;
import static com.epam.lab.beseda.news_management.util.LoggerName.ERROR_LOGGER;

@Configuration
@Import({DAOMapperConfig.class, DAOResultSetExtractorConfig.class})
@ComponentScan({"com.epam.lab.beseda.news_management.dao.entitydao"})
public class ProjectConfig {

    @Bean
    public Logger getLogger(){
        return LogManager.getLogger(ERROR_LOGGER);
    }


    @Bean
    public DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DATABASE_URL);
        dataSource.setDriverClassName(DATABASE_DRIVER_CLASS_NAME);
        dataSource.setUsername(DATABASE_USER);
        dataSource.setPassword(DATABASE_PASSWORD);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean(name="authorDAO")
    public AuthorDAO getAuthorDAO(){
        return new AuthorDAO(getJdbcTemplate());
    }

    @Bean(name="newsDAO")
    public NewsDAO getNewsDAO(){
        return new NewsDAO(getJdbcTemplate());
    }

    @Bean(name="roleDAO")
    public RoleDAO getRoleDAO(){
        return new RoleDAO(getJdbcTemplate());
    }

    @Bean(name="tagDAO")
    public TagDAO getTagDAO(){
        return new TagDAO(getJdbcTemplate());
    }

    @Bean(name="userDAO")
    public UserDAO getUserDAO(){
        return new UserDAO(getJdbcTemplate());
    }

}
