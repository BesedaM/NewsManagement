package com.epam.lab.beseda.news_management.dao.entitydao;

import com.epam.lab.beseda.news_management.config.TestConfig;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthorDAOTest.class, NewsDAOTest.class, UserDAOTest.class, RoleDAOTest.class, TagDAOTest.class})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class DAOTestSuit {

}
