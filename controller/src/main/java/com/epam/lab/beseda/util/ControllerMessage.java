package com.epam.lab.beseda.util;

public class ControllerMessage {
    public static final String IS_DELETED;
    public static final String AUTHOR_WITH_ID;
    public static final String TAG_WITH_ID;
    public static final String ROLE_WITH_ID;
    public static final String USER_WITH_ID;
    public static final String NEWS_WITH_ID;

    static {
        IS_DELETED = " is deleted";
        AUTHOR_WITH_ID = "The author entity with id=";
        TAG_WITH_ID = "The tag entity with id=";
        ROLE_WITH_ID = "The role entity with id=";
        USER_WITH_ID = "The user with id=";
        NEWS_WITH_ID = "The news with id=";
    }
}
