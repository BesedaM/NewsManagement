package com.epam.lab.beseda.util;

public class ServiceConstants {


    public static final int MIN_NAME_LENGTH;
    public static final int MAX_NAME_LENGTH;
    public static final int MIN_SURNAME_LENGTH;
    public static final int MAX_SURNAME_LENGTH;
    public static final String NON_STRING_VALUE;
    public static final String NON_ALPHANUMERIC_VALUE;
    public static final String SPACE_CHARACTER;


    public static final int MIN_TITLE_LENGTH;
    public static final int MAX_TITLE_LENGTH;
    public static final int MAX_SHORT_TEXT_LENGTH;
    public static final int MAX_FULL_TEXT_LENGTH;

    public static final int MAX_AUTHOR_NAME_LENGTH;
    public static final int MAX_AUTHOR_SURNAME_LENGTH;

    public static final int MIN_LOGIN_LENGTH;
    public static final int MAX_LOGIN_LENGTH;

    public static final int MIN_PASSWORD_LENGTH;
    public static final int MAX_PASSWORD_LENGTH;

    public static final int MIN_ENUM_VALUE_LENGTH;
    public static final int MAX_ENUM_VALUE_LENGTH;


    public static final String ALPHABETIC_VALUE;
    public static final String MAY_CONTAIN_HYPHEN;
    public static final String ONLY_ALPHANUMERIC_VALUE_MESSAGE;
    public static final String NON_SPACE_CHARACTER_MESSAGE;

    public static final String USER_WITH_LOGIN_EXISTS;

    static {

        MIN_NAME_LENGTH = 2;
        MAX_NAME_LENGTH = 20;
        MIN_SURNAME_LENGTH = 2;
        MAX_SURNAME_LENGTH = 20;
        NON_STRING_VALUE = "[\\W[0-9]&&[^-]]";
        NON_ALPHANUMERIC_VALUE = "[\\W&&[^-]]";
        SPACE_CHARACTER = "\\s";

        MIN_TITLE_LENGTH = 2;
        MAX_TITLE_LENGTH = 100;
        MAX_SHORT_TEXT_LENGTH = 500;
        MAX_FULL_TEXT_LENGTH = 5000;

        MAX_AUTHOR_NAME_LENGTH = 30;
        MAX_AUTHOR_SURNAME_LENGTH = 30;

        MIN_LOGIN_LENGTH = 4;
        MAX_LOGIN_LENGTH = 30;

        MIN_PASSWORD_LENGTH = 4;
        MAX_PASSWORD_LENGTH = 30;

        MIN_ENUM_VALUE_LENGTH = 3;
        MAX_ENUM_VALUE_LENGTH = 20;

        ALPHABETIC_VALUE = "alphabetic string";
        ONLY_ALPHANUMERIC_VALUE_MESSAGE = "only alphanumeric value";
        MAY_CONTAIN_HYPHEN = "(may contain hyphen)";
        NON_SPACE_CHARACTER_MESSAGE = "a non-space character string";

        USER_WITH_LOGIN_EXISTS = "User with such login already exists";
    }
}