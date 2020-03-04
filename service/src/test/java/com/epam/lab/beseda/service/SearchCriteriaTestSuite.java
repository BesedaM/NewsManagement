package com.epam.lab.beseda.service;

import com.epam.lab.beseda.service.search.NewsSearchByAuthorCriteriaTest;
import com.epam.lab.beseda.service.search.NewsSearchByTagsCriteriaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({NewsSearchByAuthorCriteriaTest.class, NewsSearchByTagsCriteriaTest.class})
public class SearchCriteriaTestSuite {
}
