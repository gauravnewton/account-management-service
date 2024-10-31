/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions.types;

/**
 * The type Account not found exception.
 */
public class AccountNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Account not found exception.
     *
     * @param message the message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}

