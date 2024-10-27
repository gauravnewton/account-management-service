package com.rogers.account.management.commons;

public class AppConstant {
    public static final String API = "api";
    public static final String VERSION = "v1";
    public static final String ACCOUNT = "account";
    public static final String CREATE_ACCOUNT_SUMMARY = "Create a new account";
    public static final String CREATE_ACCOUNT_DESCRIPTION = "We can create a new account using this API by providing a valid request";
    public static final String ACCOUNT_OPERATION = "Account Operations";
    public static final String ID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String PIN_CHARACTER = "1234567890";
    public static final int ID_LENGTH = 6;
    public static final int PIN_LENGTH = 4;
    public static final String BACK_SLASH = "/";
    public static final String DELETE = "delete";
    public static final String ACCOUNT_ID = "accountId";
    public static final String EMAIL = "email";
    public static final String SECURITY_PIN = "securityPIN";
    public static final String COUNT = "count";
    public static final String INVALID_COUNTRY_AND_POSTAL_COMBINATION = "404 Not Found: \"{}\"";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCOUNT_DELETED = "Account deleted successfully";
    public static final String ACCOUNT_UPDATED = "Account updated successfully";
    public static final String ACCOUNT_DELETION_STATUS_ERROR = "You need to mark account as in-active before deletion";
    public static final String ACCOUNT_NOT_FOUND = "No existing account found based on provided account id and security pin";
    public static final String DELETE_ACCOUNT_SUMMARY = "Delete existing account";
    public static final String DELETE_ACCOUNT_DESCRIPTION = "Delete an existing account by providing account id and security pin combination";
    public static final String UPDATE_ACCOUNT_DESCRIPTION = "Update existing account";
    public static final String UPDATE_ACCOUNT_SUMMARY = "Update an existing account by provide valid account id and corresponding field values";
    public static final String EITHER_ACCOUNT_ID_OR_MAIL = "Either account id or email is required";
    public static final String GET_ACCOUNT_SUMMARY = "Retrieve an existing account";
    public static final String GET_ACCOUNT_DESCRIPTION = "Retrieve an existing account either by passing account id or email id";
    public static final String GET_ACCOUNT_COUNT_DESCRIPTION = "Retrieve account count by country and then state and then city in order by state and then place";
    public static final String GET_ACCOUNT_COUNT_SUMMARY = "Retrieve account count";
    public static final String NO_ACCOUNT_EXISTS = "No any account exists into system";
}
