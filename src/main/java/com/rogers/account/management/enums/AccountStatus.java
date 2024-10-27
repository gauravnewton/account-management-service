package com.rogers.account.management.enums;

public enum AccountStatus {
    IN_ACTIVE("Inactive"),
    ACTIVE("Active"),
    REQUESTED("Requested");

    private String status;

    AccountStatus(String status) {
        this.status = status;
    }

    String getStatus(){
        return this.status;
    }

}
