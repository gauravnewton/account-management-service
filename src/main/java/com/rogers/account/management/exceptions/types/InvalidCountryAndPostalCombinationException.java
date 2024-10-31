/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions.types;

/**
 * The type Invalid country and postal combination exception.
 */
public class InvalidCountryAndPostalCombinationException extends RuntimeException {
    /**
     * Instantiates a new Invalid country and postal combination exception.
     *
     * @param message the message
     */
    public InvalidCountryAndPostalCombinationException(String message) {
        super(message);
    }
}

