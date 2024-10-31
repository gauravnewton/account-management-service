/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions.types;

/**
 * The type Account already exists exception.
 */
public class AccountAlreadyExistsException extends RuntimeException {
    /**
     * Instantiates a new Account already exists exception.
     *
     * @param message the message
     */
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
