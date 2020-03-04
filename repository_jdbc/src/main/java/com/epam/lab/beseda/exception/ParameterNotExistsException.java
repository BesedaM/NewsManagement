package com.epam.lab.beseda.exception;

public class ParameterNotExistsException extends DAOLayerException{

    public ParameterNotExistsException() {
        super();
    }

    public ParameterNotExistsException(String message) {
        super(message);
    }

    public ParameterNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterNotExistsException(Throwable cause) {
        super(cause);
    }

    public ParameterNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
