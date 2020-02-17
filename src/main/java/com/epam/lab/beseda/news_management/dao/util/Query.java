package com.epam.lab.beseda.news_management.dao.util;

import static com.epam.lab.beseda.news_management.dao.util.DBConstants.TABLE_NAME;

public class Query {

    // User DAO
    public static final String SELECT_ALL_USERS;
    public static final String SELECT_USERS_BY_ID_LIST;
    public static final String SELECT_USER_BY_ID;

    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD;
    public static final String SELECT_USER_BY_LOGIN;

    public static final String DELETE_USER_BY_ID;
    public static final String ADD_NEW_USER;
    public static final String UPDATE_USER;

    public static final String USER_ROLE_GET_ROLE;
    public static final String USER_ADD_ROLE;

    // Author DAO
    public static final String SELECT_ALL_AUTHORS;
    public static final String SELECT_AUTHORS_BY_ID_LIST;
    public static final String SELECT_AUTHOR_BY_ID;

    public static final String DELETE_AUTHOR_BY_ID;
    public static final String ADD_NEW_AUTHOR;
    public static final String UPDATE_AUTHOR;

    public static final String NEWS_AUTHOR_GET_NEWS_ID;


    // News DAO
    public static final String SELECT_ALL_NEWS;
    public static final String SELECT_NEWS_BY_ID_LIST;
    public static final String SELECT_NEWS_BY_ID;

    public static final String DELETE_NEWS_BY_ID;
    public static final String ADD_NEWS;
    public static final String UPDATE_NEWS;

    public static final String NEWS_AUTHOR_ADD_AUTHOR;
    public static final String NEWS_AUTHOR_GET_AUTHOR_ID;
    public static final String NEWS_TAG_ADD_TAG;
    public static final String NEWS_TAG_DELETE_TAG;
    public static final String NEWS_TAG_GET_TAGS_NAMES;
    public static final String NEWS_TAG_GET_TAGS;
    public static final String NEWS_TAG_GET_NEWS;
    public static final String COUNT_NEWS_NUMBER;

    // EnumEntity DAO
    public static final String SELECT_ALL_ELEMENTS;
    public static final String SELECT_ELEMENTS_BY_ID_LIST;
    public static final String SELECT_ELEMENT_BY_ID;

    public static final String DELETE_ELEMENT_BY_ID;
    public static final String ADD_ELEMENT;
    public static final String UPDATE_ELEMENT;
    public static final String SELECT_ENTITY_BY_NAME;

    static {

        // User DAO
        SELECT_ALL_USERS = "SELECT * FROM news_management.\"user\"";
        SELECT_USER_BY_ID = "SELECT * FROM news_management.\"user\" WHERE id=?";
        SELECT_USERS_BY_ID_LIST = "SELECT * FROM news_management.\"user\" WHERE id IN (?)";
        SELECT_USER_BY_LOGIN = "SELECT * FROM news_management.\"user\" WHERE login=?";
        SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM news_management.\"user\" WHERE login=? AND password=?";
        DELETE_USER_BY_ID = "DELETE FROM news_management.\"user\" WHERE id=?";
        ADD_NEW_USER = "INSERT INTO news_management.\"user\" (name, surname, login, password) VALUES (:name, :surname, :login, :password)";
        UPDATE_USER = "UPDATE news_management.\"user\" SET name=:name, surname=:surname, login=:login, password=:password WHERE id=:id";

        USER_ROLE_GET_ROLE = "SELECT roles.id, roles.name FROM news_management.\"user\" LEFT JOIN news_management.roles ON \"user\".role_id=roles.id WHERE \"user\".id=?";
        USER_ADD_ROLE = "UPDATE news_management.\"user\" SET role_id=? WHERE id=?";

        // Author DAO
        SELECT_ALL_AUTHORS = "SELECT * FROM news_management.author";
        SELECT_AUTHOR_BY_ID = "SELECT * FROM news_management.author WHERE id=?";
        SELECT_AUTHORS_BY_ID_LIST = "SELECT * FROM news_management.author WHERE id IN (?)";
        DELETE_AUTHOR_BY_ID = "DELETE FROM news_management.author WHERE id=?";
        ADD_NEW_AUTHOR = "INSERT INTO news_management.author (name, surname) VALUES (:name, :surname)";
        UPDATE_AUTHOR = "UPDATE news_management.author SET name=:name, surname=:surname WHERE id=:id";

        NEWS_AUTHOR_GET_NEWS_ID = "SELECT news_id FROM news_management.news_author WHERE author_id=?";
        NEWS_TAG_ADD_TAG = "INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (?,?)";
        NEWS_TAG_DELETE_TAG = "DELETE FROM news_management.news_tag WHERE news_id=? AND tag_id=?";


        // News DAO
        SELECT_ALL_NEWS = "SELECT * FROM news_management.news";
        SELECT_NEWS_BY_ID = "SELECT * FROM news_management.news WHERE id=?";
        SELECT_NEWS_BY_ID_LIST = "SELECT * FROM news_management.news WHERE id IN (?)";
        DELETE_NEWS_BY_ID = "DELETE FROM news_management.news WHERE id=?";
        ADD_NEWS = "INSERT INTO news_management.news (creation_date, modification_date, title, short_text, full_text)" +
                " VALUES (now(), now(), :title, :short_text, :full_text)";
        UPDATE_NEWS = "UPDATE news_management.news SET modification_date=now(), title=:title, short_text=:short_text, full_text=:full_text";

        NEWS_AUTHOR_ADD_AUTHOR = "INSERT INTO news_management.news_author (news_id, author_id) VALUES (?, ?)";
        NEWS_AUTHOR_GET_AUTHOR_ID = "SELECT author_id FROM news_management.news_author WHERE news_id=?";

        NEWS_TAG_GET_TAGS_NAMES = "SELECT tag.name \n" +
                "FROM news_management.news LEFT JOIN news_management.news_tag ON news.id=news_tag.news_id \n" +
                "LEFT JOIN news_management.tag ON news_tag.tag_id=tag.id WHERE news.id=?";
        NEWS_TAG_GET_TAGS = "SELECT tag.id, tag.name \n" +
                "FROM news_management.news LEFT JOIN news_management.news_tag ON news.id=news_tag.news_id \n" +
                "LEFT JOIN news_management.tag ON news_tag.tag_id=tag.id WHERE news.id=?";
        NEWS_TAG_GET_NEWS = "SELECT news.id FROM news_management.news LEFT JOIN news_management.news_tag ON news.id=news_tag.news_id \n" +
                "LEFT JOIN news_management.tag ON news_tag.tag_id=tag.id WHERE tag.id=?";
        COUNT_NEWS_NUMBER = "SELECT count(*) from news_management.news";


        //EnumEntity DAO
        SELECT_ALL_ELEMENTS = "SELECT * FROM news_management." + TABLE_NAME;
        SELECT_ELEMENT_BY_ID = SELECT_ALL_ELEMENTS + " WHERE id=?";
        SELECT_ENTITY_BY_NAME = SELECT_ALL_ELEMENTS + " WHERE name=?";
        SELECT_ELEMENTS_BY_ID_LIST = SELECT_ALL_ELEMENTS + " WHERE id IN (?)";
        DELETE_ELEMENT_BY_ID = "DELETE FROM news_management." + TABLE_NAME + " WHERE id=?";
        ADD_ELEMENT = "INSERT INTO news_management." + TABLE_NAME + " (name) VALUES (:name)";
        UPDATE_ELEMENT = "UPDATE news_management." + TABLE_NAME + " SET name=:name WHERE id=:id";

    }

}
