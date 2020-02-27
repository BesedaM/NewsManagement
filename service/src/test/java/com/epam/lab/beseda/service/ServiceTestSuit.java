package com.epam.lab.beseda.service;

import com.epam.lab.beseda.service.serviceclass.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthorServiceTest.class, NewsServiceTest.class, UserServiceTest.class, RoleServiceTest.class, TagServiceTest.class})
public class ServiceTestSuit {
}
