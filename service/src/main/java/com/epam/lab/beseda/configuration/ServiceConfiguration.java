package com.epam.lab.beseda.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ModelMapperConfiguration.class,ServiceValidatorConfig.class})
@ComponentScan({"com.epam.lab.beseda.service.serviceclass","com.epam.lab.beseda.service.search"})
public class ServiceConfiguration {
}
