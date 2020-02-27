package com.epam.lab.beseda.service.validator;


import com.epam.lab.beseda.configuration.ServiceValidatorConfig;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.exception.IrregularLengthException;
import com.epam.lab.beseda.exception.ValidationException;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ServiceValidatorConfig.class})
public class NewsValidatorTest {

    @Autowired
    private NewsValidator validator;

    //Null values tests

    @Test(expected = ValidationException.class)
    public void testValidate_nullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullTitle() throws ValidationException {
        NewsDTO news = new NewsDTO("Egor","Zhuk",null, "Short text", "Full text");
        validator.validate(news);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullShortText() throws ValidationException {
        NewsDTO news = new NewsDTO("Egor","Zhuk","Title", null, "Full text");
        validator.validate(news);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullFullText() throws ValidationException {
        NewsDTO news = new NewsDTO("Egor","Zhuk","Title", "Short text", null);
        validator.validate(news);
    }


    //Title length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateTitleFieldLength_small_incorrect() throws ValidationException {
        NewsDTO news = new NewsDTO("Egor","Zhuk","T", "Short text", "Full text");
        validator.validate(news);
    }

    @Test
    public void testValidateTitleFieldLength_small_correct() throws ValidationException {
        NewsDTO news = new NewsDTO("Egor","Zhuk","Ti", "Short text", "Full text");
        validator.validate(news);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateTitleFieldLength_large_incorrect() throws ValidationException {
        String text = RandomString.make(101);
        NewsDTO news = new NewsDTO("Egor","Zhuk",text, "Short text", "Full text");
        validator.validate(news);
    }

    @Test
    public void testValidateTitleFieldLength_large_correct() throws ValidationException {
        String text = RandomString.make(100);
        NewsDTO news = new NewsDTO("Egor","Zhuk",text, "Short text", "Full text");
        validator.validate(news);
    }


    //Short text length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateShortTextFieldLength_large_incorrect() throws ValidationException {
        String text = RandomString.make(501);
        NewsDTO news = new NewsDTO("Egor","Zhuk","Title", text, "Full text");
        validator.validate(news);
    }

    @Test
    public void testValidateShortTextFieldLength_large_correct() throws ValidationException {
        String text = RandomString.make(500);
        NewsDTO news = new NewsDTO("Egor","Zhuk","Title", text, "Full text");
        validator.validate(news);
    }


    //Full text length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateFullTextFieldLength_large_incorrect() throws ValidationException {
        String text = RandomString.make(5001);
        NewsDTO news = new NewsDTO("Egor","Zhuk","Title", "short text", text);
        validator.validate(news);
    }

    @Test
    public void testValidateFullTextFieldLength_large_correct() throws ValidationException {
        String text = RandomString.make(5000);
        NewsDTO news = new NewsDTO("Egor","Zhuk","Title", "short text", text);
        validator.validate(news);
    }

}
