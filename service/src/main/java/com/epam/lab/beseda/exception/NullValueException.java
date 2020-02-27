package com.epam.lab.beseda.exception;


public class NullValueException extends ValidationException {

    public NullValueException() {
        super("the entity is null");
    }

    public NullValueException(String fieldName) {
        super("the '" + fieldName + "' field must not be null");
    }

    public NullValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullValueException(Throwable cause) {
        super(cause);
    }

    public NullValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
