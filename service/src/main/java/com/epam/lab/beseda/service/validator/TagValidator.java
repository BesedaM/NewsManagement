package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.exception.validation.IrregularLengthException;
import com.epam.lab.beseda.exception.validation.IrregularStringFormatException;
import com.epam.lab.beseda.exception.validation.NullValueException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.epam.lab.beseda.util.DBEntityTable.NAME;
import static com.epam.lab.beseda.util.FieldsLimitations.MAX_ENUM_VALUE_LENGTH;
import static com.epam.lab.beseda.util.FieldsLimitations.MIN_ENUM_VALUE_LENGTH;
import static com.epam.lab.beseda.util.ServiceConstants.*;

@Component
public class TagValidator implements Validatable<TagDTO> {

    @Autowired
    @Qualifier("nonStringValuePattern")
    private Pattern pattern;

    @Override
    public void validate(TagDTO entity) throws ValidationException {
        if (entity == null) {
            throw new NullValueException();
        } else if (entity.getName() == null) {
            throw new NullValueException(NAME);
        }

        if (pattern.matcher(entity.getName()).find()) {
            throw new IrregularStringFormatException(NAME, ALPHABETIC_VALUE);
        } else if (entity.getName().length() < MIN_ENUM_VALUE_LENGTH
                ||entity.getName().length() > MAX_ENUM_VALUE_LENGTH) {
            throw new IrregularLengthException(NAME, MAX_ENUM_VALUE_LENGTH);
        }
    }
}
