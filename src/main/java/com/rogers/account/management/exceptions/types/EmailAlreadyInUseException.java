/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions.types;

/**
 * The type Email already in use exception.
 */
public class EmailAlreadyInUseException extends RuntimeException {
    /**
     * Instantiates a new Email already in use exception.
     *
     * @param message the message
     */
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}

