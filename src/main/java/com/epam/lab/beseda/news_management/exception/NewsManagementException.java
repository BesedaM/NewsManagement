package com.epam.lab.beseda.news_management.exception;

public class NewsManagementException extends Exception{

    public NewsManagementException() {
    }

    public NewsManagementException(String message) {
        super(message);
    }

    public NewsManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsManagementException(Throwable cause) {
        super(cause);
    }

    public NewsManagementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
