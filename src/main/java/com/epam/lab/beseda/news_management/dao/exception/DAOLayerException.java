package com.epam.lab.beseda.news_management.dao.exception;

import com.epam.lab.beseda.news_management.exception.NewsManagementException;

public class DAOLayerException extends NewsManagementException {
    public DAOLayerException() {
        super();
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
