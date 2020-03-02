package com.epam.lab.beseda.service.validator;


import com.epam.lab.beseda.configuration.ServiceValidatorConfig;
import com.epam.lab.beseda.dto.UserDTO;
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
public class UserValidatorTest {

    @Autowired
    private UserValidator validator;

    //Null values tests

    @Test(expected = ValidationException.class)
    public void testValidate_nullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullName() throws ValidationException {
        UserDTO user = new UserDTO(null,"Lock","clock","1234567","guest");
        validator.validate(user);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullSurname() throws ValidationException {
        UserDTO user = new UserDTO("Ken",null,"clock","1234567","guest");
        validator.validate(user);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullLogin() throws ValidationException {
        UserDTO user = new UserDTO("Ken","Lock",null,"1234567","guest");
        validator.validate(user);
    }

    @Test(expected = ValidationException.class)
    public void testValidate_nullPassword() throws ValidationException {
        UserDTO user = new UserDTO("Ken","Lock","clock",null,"guest");
        validator.validate(user);
    }

    //Name format test

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_numbers_incorrect() throws ValidationException {
        validator.validate(new UserDTO("And3rei","Lock","clock","1234567","guest"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_symbol01_incorrect() throws ValidationException {
        validator.validate(new UserDTO("An@drei","Lock","clock","1234567","guest"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateNameFieldFormat_symbol02_incorrect() throws ValidationException {
        validator.validate(new UserDTO("\"Andrei","Lock","clock","1234567","guest"));
    }

    //Surname format test

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateSurnameFieldFormat_numbers_incorrect() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock7","clock","1234567","guest"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateSurnameFieldFormat_symbol01_incorrect() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock.","clock","1234567","guest"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateSurnameFieldFormat_symbol02_incorrect() throws ValidationException {
        validator.validate(new UserDTO("Andrei"," Lock","clock","1234567","guest"));
    }

    @Test
    public void testValidateSurnameFieldFormat_hyphen_correct() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock-Nock","clock","1234567","guest"));
    }


    //Login format test

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateLoginFieldFormat_numbers_incorrect() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock","cl ock","1234567","guest"));
    }

    @Test(expected = IrregularStringFormatException.class)
    public void testValidateLoginFieldFormat_symbol01_incorrect() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock","clo=ck","1234567","guest"));
    }

    @Test
    public void testValidateLoginFieldFormat_number_correct() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock","clo9ck","1234567","guest"));
    }


    //Password field test

    @Test(expected = IrregularStringFormatException.class)
    public void testValidatePasswordFieldFormat_space_incorrect() throws ValidationException {
        validator.validate(new UserDTO("Andrei","Lock","clock","1234 567","guest"));
    }


    //Name length test

    @Test(expected = IrregularLengthException.class)
    public void testValidateNameFieldLength_small_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("A","Lock","clock","1234567","guest");
        validator.validate(user);
    }

    @Test
    public void testValidateNameFieldLength_small_correct() throws ValidationException {
        UserDTO user = new UserDTO("An","Lock","clock","1234567","guest");
        validator.validate(user);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateNameFieldLength_large_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("adadadadaOadadadadaOa","Lock","clock","1234567","guest");
        validator.validate(user);
    }

    @Test
    public void testValidateNameFieldLength_large_correct() throws ValidationException {
        UserDTO user = new UserDTO("adadadadaOadadadadaO","Lock","clock","1234567","guest");
        validator.validate(user);
    }


    //Surname length test

    @Test(expected = IrregularLengthException.class)
    public void testValidateSurnameFieldLength_small_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","L","clock","1234567","guest");
        validator.validate(user);
    }

    @Test
    public void testValidateSurnameFieldLength_small_correct() throws ValidationException {
        UserDTO user = new UserDTO("Ant","Lo","clock","1234567","guest");
        validator.validate(user);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateSurnameFieldLength_large_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","adadadadaOadadadadaOa","clock","1234567","guest");
        validator.validate(user);
    }

    @Test
    public void testValidateSurnameFieldLength_large_correct() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","adadadadaOadadadadaO","clock","1234567","guest");
        validator.validate(user);
    }


    //Login length test

    @Test(expected = IrregularLengthException.class)
    public void testValidateLoginFieldLength_small_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","Lock","clo","1234567","guest");
        validator.validate(user);
    }

    @Test
    public void testValidateLoginFieldLength_small_correct() throws ValidationException {
        UserDTO user = new UserDTO("Ant","Lock","clob","1234567","guest");
        validator.validate(user);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidateLoginFieldLength_large_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","Lock","adadadadaOadadadadaOadadadadaOa","1234567","guest");
        validator.validate(user);
    }

    @Test
    public void testValidateLoginFieldLength_large_correct() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","Lock","adadadadaOadadadadaOadadadadaO","1234567","guest");
        validator.validate(user);
    }

    //Password length test

    @Test(expected = IrregularLengthException.class)
    public void testValidatePasswordFieldLength_small_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","Lock","clone","clo","guest");
        validator.validate(user);
    }

    @Test
    public void testValidatePasswordFieldLength_small_correct() throws ValidationException {
        UserDTO user = new UserDTO("Ant","Lock","clone","clob","guest");
        validator.validate(user);
    }

    @Test(expected = IrregularLengthException.class)
    public void testValidatePasswordFieldLength_large_incorrect() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","Lock","clone","adadadadaOadadadadaOadadadadaOa","guest");
        validator.validate(user);
    }

    @Test
    public void testValidatePasswordFieldLength_large_correct() throws ValidationException {
        UserDTO user = new UserDTO("Andrei","Lock","clone","adadadadaOadadadadaOadadadadaO","guest");
        validator.validate(user);
    }


}
