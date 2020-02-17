package com.epam.lab.beseda.news_management.config;

import com.epam.lab.beseda.news_management.dao.mapper.AuthorMapper;
import com.epam.lab.beseda.news_management.dao.mapper.EnumEntityMapper;
import com.epam.lab.beseda.news_management.dao.mapper.NewsMapper;
import com.epam.lab.beseda.news_management.dao.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epam.lab.beseda.news_management.dao.mapper"})
public class DAOMapperConfig {

    @Bean(name="authorMapper")
    public AuthorMapper getAuthorMapper(){
        return new AuthorMapper();
    }

    @Bean(name="newsMapper")
    public NewsMapper getNewsMapper(){
        return new NewsMapper();
    }

    @Bean(name="userMapper")
    public UserMapper getUserMapper(){
        return new UserMapper();
    }

    @Bean(name="enumEntityMapper")
    public EnumEntityMapper getEnumEntityMapper(){
        return new EnumEntityMapper();
    }
}
