package com.epam.lab.beseda.service;

import com.epam.lab.beseda.service.modelmapper.AuthorMapperTest;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapperTest;
import com.epam.lab.beseda.service.modelmapper.NewsMapperTest;
import com.epam.lab.beseda.service.modelmapper.UserMapperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthorMapperTest.class, EnumEntityMapperTest.class, NewsMapperTest.class, UserMapperTest.class})
public class ModelMapperTestSuit {
}
