package com.epam.lab.beseda.util;

public class FieldsLimitations {

    public static final int MIN_NAME_LENGTH= 2;
    public static final int MAX_NAME_LENGTH= 20;
    public static final int MIN_SURNAME_LENGTH= 2;
    public static final int MAX_SURNAME_LENGTH= 20;

    public static final int MIN_TITLE_LENGTH= 2;
    public static final int MAX_TITLE_LENGTH= 100;
    public static final int MAX_SHORT_TEXT_LENGTH= 500;
    public static final int MAX_FULL_TEXT_LENGTH= 5000;

    public static final int MAX_AUTHOR_NAME_LENGTH= 30;
    public static final int MAX_AUTHOR_SURNAME_LENGTH= 30;

    public static final int MIN_LOGIN_LENGTH= 4;
    public static final int MAX_LOGIN_LENGTH= 30;

    public static final int MIN_PASSWORD_LENGTH= 4;
    public static final int MAX_PASSWORD_LENGTH= 30;

    public static final int MIN_ENUM_VALUE_LENGTH= 3;
    public static final int MAX_ENUM_VALUE_LENGTH = 20;

    public static final String STRING_VALUE = "[\\w[-]&&[^0-9]]+";
    public static final String ALPHANUMERIC_VALUE = "[\\w[-]]+";

}
