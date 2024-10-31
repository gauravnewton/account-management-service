/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions.types;

/**
 * The type Address look up exception.
 */
public class AddressLookUpException extends RuntimeException {
    /**
     * Instantiates a new Address look up exception.
     *
     * @param message the message
     */
    public AddressLookUpException(String message) {
        super(message);
    }
}
