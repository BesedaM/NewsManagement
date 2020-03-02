package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.configuration.ServiceValidatorConfig;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.exception.validation.IrregularLengthException;
import com.epam.lab.beseda.exception.validation.IrregularStringFormatException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ServiceValidatorConfig.class})
public class AuthorValidatorTest {

    @Autowired
    private AuthorValidator validator;

    //Null values tests

    @Test(expected = ValidationException.class)
    public void testValidate_nullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullName() throws ValidationException {
        AuthorDTO author = new AuthorDTO(null, "Keen");
        validator.validate(author);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullSurname() throws ValidationException {
        AuthorDTO author = new AuthorDTO("Dan", null);
        validator.validate(author);
    }

    //Name format test

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_numbers_incorrect() throws ValidationException {
        validator.validate(new AuthorDTO("Alex9", "Ben"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_symbol01_incorrect() throws ValidationException {
        validator.validate(new AuthorDTO("Alex,", "Ben"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_symbol02_incorrect() throws ValidationException {
        validator.validate(new AuthorDTO("Alex@", "Ben"));
    }

    //Surname format tests

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateSurnameFieldFormat_numbers_incorrect() throws ValidationException {
        validator.validate(new AuthorDTO("Alex", "Ben10"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateSurnameFieldFormat_symbol01_incorrect() throws ValidationException {
        validator.validate(new AuthorDTO("Alex", "Ben."));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateSurnameFieldFormat_symbol02_incorrect() throws ValidationException {
        validator.validate(new AuthorDTO("Alex", "Be(n"));
    }


    //Name length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateNameFieldLength_small_incorrect() throws ValidationException {
        AuthorDTO author = new AuthorDTO("V", "Ant");
        validator.validate(author);
    }

    @Test
    public void testValidateNameFieldLength_small_correct() throws ValidationException {
        AuthorDTO author = new AuthorDTO("Va", "Ant");
        validator.validate(author);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateNameFieldLength_large_incorrect() throws ValidationException {
        AuthorDTO author = new AuthorDTO("adadadadaOadadadadaOadadadadaOa", "Ant");
        validator.validate(author);
    }

    @Test
    public void testValidateNameFieldLength_large_correct() throws ValidationException {
        AuthorDTO author = new AuthorDTO("adadadadaOadadadadaOadadadadaO", "Ant");
        validator.validate(author);
    }


    //Surname length tests

    @Test(expected = IrregularLengthException.class)
    public void testValidateSurnameFieldLength_small_incorrect() throws ValidationException {
        AuthorDTO author = new AuthorDTO("Vania", "A");
        validator.validate(author);
    }

    @Test
    public void testValidateSurnameFieldLength_small_correct() throws ValidationException {
        AuthorDTO author = new AuthorDTO("Vania", "An");
        validator.validate(author);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateSurnameFieldLength_large_incorrect() throws ValidationException {
        AuthorDTO author = new AuthorDTO("Vania", "adadadadaOadadadadaOadadadadaOa");
        validator.validate(author);
    }

    @Test
    public void testValidateSurnameFieldLength_large_correct() throws ValidationException {
        AuthorDTO author = new AuthorDTO("Vania", "adadadadaOadadadadaOadadadadaO");
        validator.validate(author);
    }



}
