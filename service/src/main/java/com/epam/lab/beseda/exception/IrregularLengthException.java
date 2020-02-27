package com.epam.lab.beseda.exception;

public class IrregularLengthException extends ValidationException {

    public IrregularLengthException(String fieldName, int minLength, int maxLength) {
        super("the '" + fieldName + "' field length must be between " + minLength + " and " + maxLength);
    }

    public IrregularLengthException(String fieldName, int maxLength) {
        super("the '" + fieldName + "' field length must be not longer than " + maxLength);
    }

}
