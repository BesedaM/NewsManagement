package com.epam.lab.beseda.util;

public class DBConstants {

    public static final String OPENING_SQUARE_BRACKET;
    public static final String CLOSING_SQUARE_BRACKET;
    public static final String SPACE;
    public static final String EMPTY_CHARACTER;
    public static final String QUESTION_MARK;
    public static final String TABLE_NAME;
    public static final String TAGS_LIST;
    public static final String SPACE_SYMBOL;
    public static final String AUTHOR_ID;

    public static final String AUTHOR = "author";
    public static final String DATE = "date";
    public static final String TAG = "tag";

    public static final int ZERO_VALUE;
    public static final int MILLISECONDS_IN_SECOND;
    public static final int SECONDS_IN_MINUTE;
    public static final int MINUTES_IN_HOUR;
    public static final int HOURS_OFFSET;
    public static final int TIME_ZONE_OFFSET_IN_MILLISECONDS;

    public static final String SORTING_PARAMETER_REQUIREMENTS;

    static {
        OPENING_SQUARE_BRACKET = "[";
        CLOSING_SQUARE_BRACKET = "]";
        SPACE = " ";
        EMPTY_CHARACTER = "";
        QUESTION_MARK = "?";
        TABLE_NAME = "table_name";
        TAGS_LIST = "tags_list";
        SPACE_SYMBOL = "\\s";
        AUTHOR_ID = "author_id";

        ZERO_VALUE = 0;
        MILLISECONDS_IN_SECOND = 1000;
        SECONDS_IN_MINUTE = 60;
        MINUTES_IN_HOUR = 60;
        HOURS_OFFSET = 3;
        TIME_ZONE_OFFSET_IN_MILLISECONDS = HOURS_OFFSET * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
        SORTING_PARAMETER_REQUIREMENTS = "Sorting parameter must be in the next values: \"author\", \"date\", \"tag\"";
    }
}
