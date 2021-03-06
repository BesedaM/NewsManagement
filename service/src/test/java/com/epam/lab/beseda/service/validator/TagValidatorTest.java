package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.configuration.ServiceValidatorConfig;
import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.exception.validation.IrregularLengthException;
import com.epam.lab.beseda.exception.validation.IrregularStringFormatException;
import com.epam.lab.beseda.exception.validation.NullValueException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ServiceValidatorConfig.class})
public class TagValidatorTest {

    @Autowired
    private TagValidator validator;

    //Null values tests

    @Test(expected = NullValueException.class)
    public void testValidate_nullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test(expected = NullValueException.class)
    public void testValidate_nullName() throws ValidationException {
        TagDTO tag = new TagDTO(null);
        validator.validate(tag);
    }


    //Name format test
    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_numbers_incorrect() throws ValidationException {
        validator.validate(new TagDTO("776nbcn"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_symbol01_incorrect() throws ValidationException {
        validator.validate(new TagDTO("hjghd fgnsgn"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_symbol02_incorrect() throws ValidationException {
        validator.validate(new TagDTO("huiadb&"));
    }


    //Name length test

    @Test(expected = IrregularLengthException.class)
    public void testValidateNameFieldLength_small_incorrect() throws ValidationException {
        TagDTO entity = new TagDTO("Jo");
        validator.validate(entity);
    }

    @Test
    public void testValidateNameFieldLength_small_correct() throws ValidationException {
        TagDTO entity = new TagDTO("Jot");
        validator.validate(entity);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateNameFieldLength_large_incorrect() throws ValidationException {
        TagDTO entity = new TagDTO("adadadadaOadadadadaOa");
        validator.validate(entity);
    }

    @Test
    public void testValidateNameFieldLength_large_correct() throws ValidationException {
        TagDTO entity = new TagDTO("adadadadaOadadadadaO");
        validator.validate(entity);
    }
}
