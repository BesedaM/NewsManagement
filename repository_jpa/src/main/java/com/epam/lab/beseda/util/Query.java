package com.epam.lab.beseda.util;

public class Query {

    // User DAO
    public static final String SELECT_ALL_USERS;
    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD;
    public static final String SELECT_USER_BY_LOGIN;

    // Author DAO
    public static final String SELECT_ALL_AUTHORS;
    public static final String SELECT_AUTHOR_BY_NAME_SURNAME;

    //RoleDAO
    public static final String SELECT_ALL_ROLES;
    public static final String GET_ROLE_BY_NAME;

    //TagDAO
    public static final String SELECT_ALL_TAGS;
    public static final String GET_TAG_BY_NAME;

    // News DAO
    public static final String SELECT_ALL_NEWS;

    public static final String SEARCH_NEWS_BY_AUTHOR;
    public static final String SEARCH_NEWS_BY_TAG;

    public static final String NEWS_ORDER_BY_AUTHOR;
    public static final String NEWS_ORDER_BY_CREATION_DATE;

    public static final String DELETE_NEWS_BY_ID;

    public static final String NEWS_AUTHOR_DELETE_DEPENDENCY;
    public static final String NEWS_TAG_DELETE_ALL_TAGS;
    public static final String NEWS_TAG_DELETE_TAG;
    public static final String COUNT_NEWS_NUMBER;


    static {

        // User DAO
        SELECT_ALL_USERS = "SELECT u FROM User u order by u.id";

        SELECT_USER_BY_LOGIN = "FROM User as u WHERE u.login=:login";
        SELECT_USER_BY_LOGIN_AND_PASSWORD = "FROM User as u WHERE u.login=:login AND u.password=:password";

        // Author DAO
        SELECT_ALL_AUTHORS = "SELECT a FROM Author a order by a.id";
        SELECT_AUTHOR_BY_NAME_SURNAME = "FROM Author as a WHERE a.name=:name AND a.surname=:surname";

        NEWS_TAG_DELETE_TAG = "DELETE FROM news_management.news_tag WHERE news_id=? AND tag_id=?";

        NEWS_AUTHOR_DELETE_DEPENDENCY="DELETE FROM news_management.news_author WHERE news_id=?";
        NEWS_TAG_DELETE_ALL_TAGS="DELETE FROM news_management.news_tag WHERE news_id=?";



        //RoleDAO
        SELECT_ALL_ROLES = "SELECT r FROM Role r order by r.id";
        GET_ROLE_BY_NAME = "FROM Role as r WHERE r.name=:name";


        //TagDAO
        SELECT_ALL_TAGS = "SELECT t FROM Tag t order by t.id";
        GET_TAG_BY_NAME = "FROM Tag as t WHERE t.name=:name";



        // News DAO
        SELECT_ALL_NEWS = "SELECT n FROM News n order by n.id";

        NEWS_ORDER_BY_AUTHOR = "SELECT n FROM News as n " +
                "ORDER BY n.author.surname, n.author.name";
        NEWS_ORDER_BY_CREATION_DATE = "SELECT n FROM News n " +
                "ORDER BY n.creation_date, n.id";

        SEARCH_NEWS_BY_AUTHOR = "SELECT n FROM News n WHERE n.author.name=:name AND n.author.surname=:surname";
        SEARCH_NEWS_BY_TAG = "SELECT n FROM News n, IN (n.tags) t WHERE t IN (:search_list) GROUP BY n.id, n.author.id";

        DELETE_NEWS_BY_ID = "DELETE FROM news_management.news WHERE id=?";
        COUNT_NEWS_NUMBER = "SELECT count(*) from News";

    }

}
