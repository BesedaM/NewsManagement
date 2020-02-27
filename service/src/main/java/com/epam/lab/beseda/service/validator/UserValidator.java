package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.exception.IrregularLengthException;
import com.epam.lab.beseda.exception.IrregularStringFormatException;
import com.epam.lab.beseda.exception.NullValueException;
import com.epam.lab.beseda.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.ServiceConstants.*;

@Component
public class UserValidator implements Validatable<UserDTO> {

    @Autowired
    @Qualifier("nonStringValuePattern")
    private Pattern nonStringValuePattern;

    @Autowired
    @Qualifier("nonAlphanumericValuePattern")
    private Pattern nonAlphanumericValuePattern;

    @Autowired
    @Qualifier("spaceValuePattern")
    private Pattern spaceValuePattern;


    @Override
    public void validate(UserDTO entity) throws ValidationException {
        this.validateNullValues(entity);
        this.validateFieldsContent(entity);
        this.validateFieldsLength(entity);
    }


    private void validateNullValues(UserDTO entity) throws NullValueException {
        if (entity == null) {
            throw new NullValueException();
        } else if (entity.getName() == null) {
            throw new NullValueException(NAME);
        } else if (entity.getSurname() == null) {
            throw new NullValueException(SURNAME);
        } else if (entity.getLogin() == null) {
            throw new NullValueException(LOGIN);
        } else if (entity.getPassword() == null) {
            throw new NullValueException(PASSWORD);
        } else if (entity.getRole() == null) {
            throw new NullValueException(ROLE);
        }
    }


    private void validateFieldsContent(UserDTO entity) throws IrregularStringFormatException {
        if (nonStringValuePattern.matcher(entity.getName()).find()) {
            throw new IrregularStringFormatException(NAME, ALPHABETIC_VALUE);
        } else if (nonStringValuePattern.matcher(entity.getSurname()).find()) {
            throw new IrregularStringFormatException(SURNAME, ALPHABETIC_VALUE + MAY_CONTAIN_HYPHEN);
        } else if (nonAlphanumericValuePattern.matcher(entity.getLogin()).find()) {
            throw new IrregularStringFormatException(LOGIN, NON_ALPHANUMERIC_VALUE);
        } else if (spaceValuePattern.matcher(entity.getPassword()).find()) {
            throw new IrregularStringFormatException(PASSWORD, NON_SPACE_CHARACTER_MESSAGE);
        } else if (nonStringValuePattern.matcher(entity.getRole()).find()) {
            throw new IrregularStringFormatException(ROLE, ALPHABETIC_VALUE);
        }
    }


    private void validateFieldsLength(UserDTO entity) throws IrregularLengthException {
        if (entity.getName().length() < MIN_NAME_LENGTH
                || entity.getName().length() > MAX_NAME_LENGTH) {
            throw new IrregularLengthException(NAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
        } else if (entity.getSurname().length() < MIN_SURNAME_LENGTH
                || entity.getSurname().length() > MAX_SURNAME_LENGTH) {
            throw new IrregularLengthException(SURNAME, MIN_SURNAME_LENGTH, MAX_SURNAME_LENGTH);
        } else if (entity.getLogin().length() < MIN_LOGIN_LENGTH
                || entity.getLogin().length() > MAX_LOGIN_LENGTH) {
            throw new IrregularLengthException(LOGIN, MIN_LOGIN_LENGTH, MAX_LOGIN_LENGTH);
        } else if (entity.getPassword().length() < MIN_PASSWORD_LENGTH
                || entity.getPassword().length() > MAX_PASSWORD_LENGTH) {
            throw new IrregularLengthException(PASSWORD, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
        } else if (entity.getRole().length() < MIN_ENUM_VALUE_LENGTH
                || entity.getRole().length() > MAX_ENUM_VALUE_LENGTH) {
            throw new IrregularLengthException(ROLE, MIN_ENUM_VALUE_LENGTH, MAX_ENUM_VALUE_LENGTH);
        }
    }
}
