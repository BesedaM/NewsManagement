package com.epam.lab.beseda.configuration;

import com.epam.lab.beseda.dao.resultsetextractor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epam.lab.com.epam.lab.beseda.dao.resultsetextractor"})
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

    @Bean(name="roleExtractor")
    public RoleExtractor getRoleMapper(){
        return new RoleExtractor();
    }

    @Bean(name="tagExtractor")
    public TagExtractor getTagMapper(){
        return new TagExtractor();
    }

}
