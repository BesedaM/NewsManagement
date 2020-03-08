package com.epam.lab.beseda.util;

public class FullDataRequiredMessage {

    public static final String NEWS_DATA_REQUIRED;
    public static final String FULL_USER_DATA_REQUIRED;

    static {
        NEWS_DATA_REQUIRED = "You have to specify all the required fields, " +
                "such as 'author', 'title', 'shortText', 'fullText'. The field 'tags' is not necessary";
        FULL_USER_DATA_REQUIRED = "You have to specify all the fields, " +
                "such as 'name', 'surname', 'login', 'password', 'role'";
    }
}
