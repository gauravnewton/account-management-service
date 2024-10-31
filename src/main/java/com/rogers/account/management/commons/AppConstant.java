/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.commons;

/**
 * The type App constant.
 */
public class AppConstant {
    /**
     * The constant API.
     */
    public static final String API = "api";
    /**
     * The constant VERSION.
     */
    public static final String VERSION = "v1";
    /**
     * The constant ACCOUNT.
     */
    public static final String ACCOUNT = "account";
    /**
     * The constant CREATE_ACCOUNT_SUMMARY.
     */
    public static final String CREATE_ACCOUNT_SUMMARY = "Create a new account";
    /**
     * The constant CREATE_ACCOUNT_DESCRIPTION.
     */
    public static final String CREATE_ACCOUNT_DESCRIPTION = "We can create a new account using this API by providing a valid request";
    /**
     * The constant ACCOUNT_OPERATION.
     */
    public static final String ACCOUNT_OPERATION = "Account Operations";
    /**
     * The constant ID_CHARACTERS.
     */
    public static final String ID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    /**
     * The constant PIN_CHARACTER.
     */
    public static final String PIN_CHARACTER = "1234567890";
    /**
     * The constant ID_LENGTH.
     */
    public static final int ID_LENGTH = 6;
    /**
     * The constant PIN_LENGTH.
     */
    public static final int PIN_LENGTH = 4;
    /**
     * The constant BACK_SLASH.
     */
    public static final String BACK_SLASH = "/";
    /**
     * The constant DELETE.
     */
    public static final String DELETE = "delete";
    /**
     * The constant ACCOUNT_ID.
     */
    public static final String ACCOUNT_ID = "accountId";
    /**
     * The constant EMAIL.
     */
    public static final String EMAIL = "email";
    /**
     * The constant SECURITY_PIN.
     */
    public static final String SECURITY_PIN = "securityPIN";
    /**
     * The constant COUNT.
     */
    public static final String COUNT = "count";
    /**
     * The constant INVALID_COUNTRY_AND_POSTAL_COMBINATION.
     */
    public static final String INVALID_COUNTRY_AND_POSTAL_COMBINATION = "404 Not Found: \"{}\"";
    /**
     * The constant APPLICATION_JSON.
     */
    public static final String APPLICATION_JSON = "application/json";
    /**
     * The constant ACCOUNT_DELETED.
     */
    public static final String ACCOUNT_DELETED = "Account deleted successfully";
    /**
     * The constant ACCOUNT_UPDATED.
     */
    public static final String ACCOUNT_UPDATED = "Account updated successfully";
    /**
     * The constant ACCOUNT_DELETION_STATUS_ERROR.
     */
    public static final String ACCOUNT_DELETION_STATUS_ERROR = "You need to mark account as in-active before deletion";
    /**
     * The constant ACCOUNT_NOT_FOUND.
     */
    public static final String ACCOUNT_NOT_FOUND = "No existing account found based on provided account id and security pin";
    /**
     * The constant DELETE_ACCOUNT_SUMMARY.
     */
    public static final String DELETE_ACCOUNT_SUMMARY = "Delete existing account";
    /**
     * The constant DELETE_ACCOUNT_DESCRIPTION.
     */
    public static final String DELETE_ACCOUNT_DESCRIPTION = "Delete an existing account by providing account id and security pin combination";
    /**
     * The constant UPDATE_ACCOUNT_DESCRIPTION.
     */
    public static final String UPDATE_ACCOUNT_DESCRIPTION = "Update existing account";
    /**
     * The constant UPDATE_ACCOUNT_SUMMARY.
     */
    public static final String UPDATE_ACCOUNT_SUMMARY = "Update an existing account by provide valid account id and corresponding field values";
    /**
     * The constant EITHER_ACCOUNT_ID_OR_MAIL.
     */
    public static final String EITHER_ACCOUNT_ID_OR_MAIL = "Either account id or email is required";
    /**
     * The constant GET_ACCOUNT_SUMMARY.
     */
    public static final String GET_ACCOUNT_SUMMARY = "Retrieve an existing account";
    /**
     * The constant GET_ACCOUNT_DESCRIPTION.
     */
    public static final String GET_ACCOUNT_DESCRIPTION = "Retrieve an existing account either by passing account id or email id";
    /**
     * The constant GET_ACCOUNT_COUNT_DESCRIPTION.
     */
    public static final String GET_ACCOUNT_COUNT_DESCRIPTION = "Retrieve account count by country and then state and then city in order by state and then place";
    /**
     * The constant GET_ACCOUNT_COUNT_SUMMARY.
     */
    public static final String GET_ACCOUNT_COUNT_SUMMARY = "Retrieve account count";
    /**
     * The constant NO_ACCOUNT_EXISTS.
     */
    public static final String NO_ACCOUNT_EXISTS = "No any account exists into system";
    /**
     * The constant APP_TITLE.
     */
    public static final String APP_TITLE = "Account Management Service API";
    /**
     * The constant APP_DESCRIPTION.
     */
    public static final String APP_DESCRIPTION = "Basic CRUD Operation for Account Management";
}
