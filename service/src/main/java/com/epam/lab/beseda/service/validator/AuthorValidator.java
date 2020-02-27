package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.exception.IrregularLengthException;
import com.epam.lab.beseda.exception.IrregularStringFormatException;
import com.epam.lab.beseda.exception.NullValueException;
import com.epam.lab.beseda.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.epam.lab.beseda.util.DBEntityTable.NAME;
import static com.epam.lab.beseda.util.DBEntityTable.SURNAME;
import static com.epam.lab.beseda.util.ServiceConstants.*;

@Component
public class AuthorValidator implements Validatable<AuthorDTO> {

    @Autowired
    @Qualifier("nonStringValuePattern")
    private Pattern pattern;


    @Override
    public void validate(AuthorDTO entity) throws ValidationException {
        this.validateNullValues(entity);
        this.validateFieldsContent(entity);
        this.validateFieldsLength(entity);
    }


    private void validateNullValues(AuthorDTO entity) throws NullValueException {
        if (entity == null) {
            throw new NullValueException();
        } else if (entity.getName() == null) {
            throw new NullValueException(NAME);
        } else if (entity.getSurname() == null) {
            throw new NullValueException(SURNAME);
        }
    }


    private void validateFieldsContent(AuthorDTO entity) throws IrregularStringFormatException {
        if (pattern.matcher(entity.getName()).find()) {
            throw new IrregularStringFormatException(NAME, ALPHABETIC_VALUE);
        } else if (pattern.matcher(entity.getSurname()).find()) {
            throw new IrregularStringFormatException(SURNAME, ALPHABETIC_VALUE);
        }
    }


    private void validateFieldsLength(AuthorDTO entity) throws IrregularLengthException {
        if (entity.getName().length() < MIN_NAME_LENGTH
                || entity.getName().length() > MAX_AUTHOR_NAME_LENGTH) {
            throw new IrregularLengthException(NAME, MIN_NAME_LENGTH, MAX_AUTHOR_NAME_LENGTH);
        } else if (entity.getSurname().length() < MIN_SURNAME_LENGTH
                || entity.getSurname().length() > MAX_AUTHOR_SURNAME_LENGTH) {
            throw new IrregularLengthException(SURNAME, MIN_SURNAME_LENGTH, MAX_AUTHOR_SURNAME_LENGTH);
        }
    }

}
