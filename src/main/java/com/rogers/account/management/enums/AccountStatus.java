/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.enums;

/**
 * The enum Account status.
 */
public enum AccountStatus {
    /**
     * In active account status.
     */
    IN_ACTIVE("Inactive"),
    /**
     * Active account status.
     */
    ACTIVE("Active"),
    /**
     * Requested account status.
     */
    REQUESTED("Requested");

    private String status;

    AccountStatus(String status) {
        this.status = status;
    }

    /**
     * Get status string.
     *
     * @return the string
     */
    String getStatus(){
        return this.status;
    }

}
