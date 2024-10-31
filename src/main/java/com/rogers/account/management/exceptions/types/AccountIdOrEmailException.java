/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions.types;

/**
 * The type Account id or email exception.
 */
public class AccountIdOrEmailException extends RuntimeException {
    /**
     * Instantiates a new Account id or email exception.
     *
     * @param message the message
     */
    public AccountIdOrEmailException(String message) {
        super(message);
    }
}

