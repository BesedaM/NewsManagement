package com.epam.lab.beseda.configuration;

import com.epam.lab.beseda.service.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

import static com.epam.lab.beseda.util.ServiceConstants.*;

@Configuration
@ComponentScan({"com.epam.lab.beseda.news_management.service.validator"})
public class ServiceValidatorConfig {

    @Bean(name="nonStringValuePattern")
    public Pattern getPattern(){return Pattern.compile(NON_STRING_VALUE);}

    @Bean(name="nonAlphanumericValuePattern")
    public Pattern getNonAlphanumericPattern(){return Pattern.compile(NON_ALPHANUMERIC_VALUE);}

    @Bean(name="spaceValuePattern")
    public Pattern getSpacePattern(){return Pattern.compile(SPACE_CHARACTER);}

    @Bean(name = "authorValidator")
    public AuthorValidator getAuthorValidator(){return new AuthorValidator();}

    @Bean(name="userValidator")
    public UserValidator getUserValidator(){return new UserValidator();}

    @Bean(name="newsValidator")
    public NewsValidator getNewsValidator(){return new NewsValidator();}

    @Bean(name="tagValidator")
    public TagValidator getTagValidator(){return new TagValidator();}

    @Bean(name="roleValidator")
    public RoleValidator getRoleValidator(){return new RoleValidator();}
}
