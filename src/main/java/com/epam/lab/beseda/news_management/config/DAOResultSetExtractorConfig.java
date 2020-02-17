package com.epam.lab.beseda.news_management.config;

import com.epam.lab.beseda.news_management.dao.resultsetextractor.AuthorExtractor;
import com.epam.lab.beseda.news_management.dao.resultsetextractor.EnumEntityExtractor;
import com.epam.lab.beseda.news_management.dao.resultsetextractor.NewsExtractor;
import com.epam.lab.beseda.news_management.dao.resultsetextractor.UserExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epam.lab.beseda.news_management.dao.resultsetextractor"})
public class DAOResultSetExtractorConfig {

    @Bean(name="authorExtractor")
    public AuthorExtractor getAuthorMapper(){
        return new AuthorExtractor();
    }

    @Bean(name="newsExtractor")
    public NewsExtractor getNewsMapper(){
        return new NewsExtractor();
    }

    @Bean(name="userExtractor")
    public UserExtractor getUserMapper(){
        return new UserExtractor();
    }

    @Bean(name="enumEntityExtractor")
    public EnumEntityExtractor getEnumEntityMapper(){
        return new EnumEntityExtractor();
    }
}
