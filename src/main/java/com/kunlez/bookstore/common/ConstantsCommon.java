package com.kunlez.bookstore.common;

public class ConstantsCommon {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 30*24*60*60;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final String EMAIL_ADMIN = "nguyenduykhanh1025@gmail.com";
    public static final String USER_NAME = "USER_NAME";

    // static sort
    public static final  int SORT_ITEM_FOLLOW_DATE_UPDATE = 0; // sort follow date update
    public static final  int SORT_ITEM_FOLLOW_CREATE_AT = 1;
    public static final  int SORT_ITEM_FOLLOW_NAME = 2;

    public  static final  int ID_CATEGORIES_NONE = -1;
}
