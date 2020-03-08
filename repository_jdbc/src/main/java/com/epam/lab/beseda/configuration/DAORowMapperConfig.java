package com.epam.lab.beseda.configuration;

import com.epam.lab.beseda.dao.rowmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epam.lab.com.epam.lab.beseda.dao.rowmapper"})
public class DAORowMapperConfig {

    @Bean(name="authorRowMapper")
    public AuthorRowMapper getAuthorRowMapper(){
        return new AuthorRowMapper();
    }

    @Bean(name="newsRowMapper")
    public NewsRowMapper getNewsRowMapper(){
        return new NewsRowMapper();
    }

    @Bean(name="userRowMapper")
    public UserRowMapper getUserRowMapper(){
        return new UserRowMapper();
    }

    @Bean(name="roleRowMapper")
    public RoleRowMapper getRoleRowMapper(){
        return new RoleRowMapper();
    }

    @Bean(name="tagRowMapper")
    public TagRowMapper getTagRowMapper(){
        return new TagRowMapper();
    }
}
