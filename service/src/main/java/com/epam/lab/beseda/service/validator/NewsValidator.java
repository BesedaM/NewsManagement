package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.exception.validation.IrregularLengthException;
import com.epam.lab.beseda.exception.validation.NullValueException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.epam.lab.beseda.util.DBEntityTable.*;
import static com.epam.lab.beseda.util.FieldsLimitations.*;

@Component
public class NewsValidator implements Validatable<NewsDTO> {

    @Autowired
    @Qualifier("authorValidator")
    private AuthorValidator authorValidator;

    @Override
    public void validate(NewsDTO entity) throws ValidationException {
        this.validateNullValues(entity);
        this.validateFieldsLength(entity);
        authorValidator.validate(entity.getAuthor());
        validateTags(entity.getTags());
    }


    private void validateNullValues(NewsDTO entity) throws NullValueException {
        if (entity == null) {
            throw new NullValueException();
        } else if (entity.getTitle() == null) {
            throw new NullValueException(TITLE);
        } else if (entity.getShort_text() == null) {
            throw new NullValueException(SHORT_TEXT);
        } else if (entity.getFull_text() == null) {
            throw new NullValueException(FULL_TEXT);
        }
    }


    private void validateFieldsLength(NewsDTO entity) throws IrregularLengthException {
        if (entity.getTitle().length() < MIN_TITLE_LENGTH
                || entity.getTitle().length() > MAX_TITLE_LENGTH) {
            throw new IrregularLengthException(TITLE, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH);
        } else if (entity.getShort_text().length() > MAX_SHORT_TEXT_LENGTH) {
            throw new IrregularLengthException(SHORT_TEXT, MAX_SHORT_TEXT_LENGTH);
        } else if (entity.getFull_text().length() > MAX_FULL_TEXT_LENGTH) {
            throw new IrregularLengthException(FULL_TEXT, MAX_FULL_TEXT_LENGTH);
        }
    }

    private void validateTags(Set<String> tags) throws IrregularLengthException {
        for (String tag : tags) {
            if (tag.length() < MIN_ENUM_VALUE_LENGTH || tag.length() > MAX_ENUM_VALUE_LENGTH) {
                throw new IrregularLengthException(TAG, MIN_ENUM_VALUE_LENGTH, MAX_ENUM_VALUE_LENGTH);
            }
        }
    }
}
