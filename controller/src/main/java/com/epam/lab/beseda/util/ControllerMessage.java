package com.epam.lab.beseda.util;

public class ControllerMessage {
    public static final String IS_DELETED;
    public static final String AUTHOR_WITH_ID;
    public static final String TAG_WITH_ID;
    public static final String ROLE_WITH_ID;
    public static final String USER_WITH_ID;
    public static final String NEWS_WITH_ID;
    public static final String NEWS_DATA_REQUIRED;
    public static final String FULL_AUTHOR_DATA_REQUIRED;
    public static final String AUTHOR_ID_REQUIRED;
    public static final String TAGS_LIST_REQUIRED;
    public static final String FULL_USER_DATA_REQUIRED;

    static {
        IS_DELETED = " is deleted";
        AUTHOR_WITH_ID = "The author entity with id=";
        TAG_WITH_ID = "The tag entity with id=";
        ROLE_WITH_ID = "The role entity with id=";
        USER_WITH_ID = "The user with id=";
        NEWS_WITH_ID = "The news with id=";
        NEWS_DATA_REQUIRED = "You have to specify all the required fields, " +
                "such as 'author', 'title', 'shortText', 'fullText'. The field 'tags' is not necessary";
        FULL_AUTHOR_DATA_REQUIRED = "You have to specify the author data, such as: 'name', 'surname'";
        AUTHOR_ID_REQUIRED="You have to specify all the author data, such as:'id', 'name', 'surname'";
        TAGS_LIST_REQUIRED = "Tags list must contain of at least one tag";
        FULL_USER_DATA_REQUIRED = "You have to specify all the fields, " +
                "such as 'name', 'surname', 'login', 'password', 'role'";
    }
}
