package com.epam.lab.beseda.configuration;

import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import com.epam.lab.beseda.service.modelmapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@ComponentScan({"com.epam.lab.beseda.news_management.service.modelmapper"})
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    @Bean(name="enumEntityMapper")
    public EnumEntityMapper getEnumEntityMapper() {
        return new EnumEntityMapper();
    }

    @Bean(name="authorMapper")
    public AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }

    @Bean(name="userMapper")
    public UserMapper getUserMapper() {
        return new UserMapper();
    }

    @Bean(name="newsMapper")
    public NewsMapper getNewsMapper() {
        return new NewsMapper();
    }
}
