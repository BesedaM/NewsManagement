package com.epam.lab.beseda.dao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.entitydao.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthorDAOTest.class, NewsDAOTest.class, UserDAOTest.class, RoleDAOTest.class, TagDAOTest.class})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class DAOTestSuite {

}
