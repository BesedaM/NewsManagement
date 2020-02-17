package com.epam.lab.beseda.news_management.dao.util;

import java.util.ResourceBundle;

public class DatabaseProperties {

    private static final String DATABASE_PROP_FILE_NAME;
    private static final ResourceBundle DATABASE_PROP;

    public static final String DATABASE_URL;

    public static final String DATABASE_USER;
    public static final String DATABASE_PASSWORD;

    public static final String DATABASE_ENCODING;
    public static final String DATABASE_DRIVER_CLASS_NAME;

    private static final String UNICODE_PATCH;
    private static final String TIMEZONE_PATCH;

    private DatabaseProperties() {
    }

    static {
        DATABASE_PROP_FILE_NAME = "database";
        UNICODE_PATCH = "?useUnicode=true&characterEncoding=utf-8";
        TIMEZONE_PATCH = "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        DATABASE_PROP = ResourceBundle.getBundle(DATABASE_PROP_FILE_NAME);
        DATABASE_URL = DATABASE_PROP.getString("DATABASE_URL").trim() + UNICODE_PATCH + TIMEZONE_PATCH;
        DATABASE_USER = DATABASE_PROP.getString("DATABASE_USER").trim();
        DATABASE_PASSWORD = DATABASE_PROP.getString("DATABASE_PASSWORD").trim();
        DATABASE_ENCODING = DATABASE_PROP.getString("DATABASE_ENCODING").trim();
        DATABASE_DRIVER_CLASS_NAME=DATABASE_PROP.getString("DATABASE_DRIVER_CLASS_NAME").trim();
    }
}
