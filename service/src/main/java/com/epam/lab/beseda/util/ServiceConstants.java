package com.epam.lab.beseda.util;

public class ServiceConstants {

    public static final String NON_STRING_VALUE;
    public static final String NON_ALPHANUMERIC_VALUE;
    public static final String SPACE_CHARACTER;

    public static final String ALPHABETIC_VALUE;
    public static final String MAY_CONTAIN_HYPHEN;
    public static final String ONLY_ALPHANUMERIC_VALUE_MESSAGE;
    public static final String NON_SPACE_CHARACTER_MESSAGE;

    public static final String USER_WITH_LOGIN_EXISTS;

    static {
        NON_STRING_VALUE = "[\\W[0-9]&&[^-]]";
        NON_ALPHANUMERIC_VALUE = "[\\W&&[^-]]";
        SPACE_CHARACTER = "\\s";

        ALPHABETIC_VALUE = "alphabetic string";
        ONLY_ALPHANUMERIC_VALUE_MESSAGE = "only alphanumeric value";
        MAY_CONTAIN_HYPHEN = "(may contain hyphen)";
        NON_SPACE_CHARACTER_MESSAGE = "a non-space character string";

        USER_WITH_LOGIN_EXISTS = "User with such login already exists";
    }
}
