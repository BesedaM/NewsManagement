package com.epam.lab.beseda.service.validator;


import com.epam.lab.beseda.configuration.ServiceValidatorConfig;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.exception.validation.IrregularLengthException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import net.bytebuddy.utility.RandomString;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ServiceValidatorConfig.class})
public class NewsValidatorTest {

    @Autowired
    private NewsValidator validator;

    private static AuthorDTO authorDTO;

    @BeforeClass
    public static void init() {
        authorDTO = new AuthorDTO("Egor", "Zhuk");
    }

    //Null values tests

    @Test(expected = ValidationException.class)
    public void testValidate_nullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullTitle() throws ValidationException {
        NewsDTO news = new NewsDTO(authorDTO, null, "Short text", "Full text");
        validator.validate(news);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullShortText() throws ValidationException {
        NewsDTO news = new NewsDTO(authorDTO, "Title", null, "Full text");
        validator.validate(news);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullFullText() throws ValidationException {
        NewsDTO news = new NewsDTO(authorDTO, "Title", "Short text", null);
        validator.validate(news);
    }


    //Title length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateTitleFieldLength_small_incorrect() throws ValidationException {
        NewsDTO news = new NewsDTO(authorDTO, "T", "Short text", "Full text");
        validator.validate(news);
    }

    @Test
    public void testValidateTitleFieldLength_small_correct() throws ValidationException {
        NewsDTO news = new NewsDTO(authorDTO, "Ti", "Short text", "Full text");
        validator.validate(news);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateTitleFieldLength_large_incorrect() throws ValidationException {
        String text = RandomString.make(101);
        NewsDTO news = new NewsDTO(authorDTO, text, "Short text", "Full text");
        validator.validate(news);
    }

    @Test
    public void testValidateTitleFieldLength_large_correct() throws ValidationException {
        String text = RandomString.make(100);
        NewsDTO news = new NewsDTO(authorDTO, text, "Short text", "Full text");
        validator.validate(news);
    }


    //Short text length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateShortTextFieldLength_large_incorrect() throws ValidationException {
        String text = RandomString.make(501);
        NewsDTO news = new NewsDTO(authorDTO, "Title", text, "Full text");
        validator.validate(news);
    }

    @Test
    public void testValidateShortTextFieldLength_large_correct() throws ValidationException {
        String text = RandomString.make(500);
        NewsDTO news = new NewsDTO(authorDTO, "Title", text, "Full text");
        validator.validate(news);
    }


    //Full text length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateFullTextFieldLength_large_incorrect() throws ValidationException {
        String text = RandomString.make(5001);
        NewsDTO news = new NewsDTO(authorDTO, "Title", "short text", text);
        validator.validate(news);
    }

    @Test
    public void testValidateFullTextFieldLength_large_correct() throws ValidationException {
        String text = RandomString.make(5000);
        NewsDTO news = new NewsDTO(authorDTO, "Title", "short text", text);
        validator.validate(news);
    }

    //Validate tags
    @Test(expected = ValidationException.class)
    public void testValidateTags_tooSmall() throws ValidationException {
        String text = RandomString.make(100);
        Set<String> tags = new HashSet<>();
        tags.add("1233");
        tags.add("tt");
        NewsDTO news = new NewsDTO(authorDTO, "Title", "short text", text);
        news.setTags(tags);
        validator.validate(news);
    }

    @Test(expected = ValidationException.class)
    public void testValidateTags_tooBig() throws ValidationException {
        String text = RandomString.make(100);
        String bigTag = RandomString.make(21);
        Set<String> tags = new HashSet<>();
        tags.add("1233");
        tags.add(bigTag);
        NewsDTO news = new NewsDTO(authorDTO, "Title", "short text", text);
        news.setTags(tags);
        validator.validate(news);
    }

}
