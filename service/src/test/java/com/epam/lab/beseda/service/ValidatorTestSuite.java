package com.epam.lab.beseda.service;

import com.epam.lab.beseda.service.validator.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthorValidatorTest.class, NewsValidatorTest.class, RoleValidatorTest.class, TagValidatorTest.class, UserValidatorTest.class})
public class ValidatorTestSuite {
}
