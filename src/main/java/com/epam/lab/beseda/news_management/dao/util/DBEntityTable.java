package com.epam.lab.beseda.news_management.dao.util;

public class DBEntityTable {

    public static final String DATABASE_NAME = "news_management";

    public static final String T_USER = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER_ROLE_ID = "role_id";

    public static final String T_AUTHOR = "author";

    public static final String T_NEWS = "news";
    public static final String TITLE = "title";
    public static final String SHORT_TEXT = "short_text";
    public static final String FULL_TEXT = "full_text";
    public static final String CREATION_DATE = "creation_date";
    public static final String MODIFICATION_DATE = "modification_date";

    public static final String ENTITY_NAME = "name";

    public static final String T_TAG = "tag";
    public static final String TAG_NAME = "name";

    public static final String T_ROLES = "roles";
    public static final String ROLE_NAME = "name";

    public static final String T_NEWS_TAG = "news_tag";
    public static final String NEWS_ID = "news_id";
    public static final String TAG_ID = "tag_id";

    public static final String T_NEWS_AUTHOR = "news_tag";
    public static final String AUTHOR_ID = "author_id";

}
