package com.epam.lab.beseda.exception.validation;

public class IrregularStringFormatException extends ValidationException {

    public IrregularStringFormatException(String fieldName, String expectedFormat) {
        super("the '" + fieldName + "' must be " + expectedFormat);
    }


}
