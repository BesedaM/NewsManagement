package com.epam.lab.beseda.exception;

public class DAOLayerException extends Exception{

    public DAOLayerException() {
    }

    public DAOLayerException(String message) {
        super(message);
    }

    public DAOLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOLayerException(Throwable cause) {
        super(cause);
    }

    public DAOLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
