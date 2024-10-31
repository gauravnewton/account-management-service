/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.services;

import com.rogers.account.management.dtos.request.CreateAccountRequest;
import com.rogers.account.management.dtos.response.AccountCountResponse;
import com.rogers.account.management.dtos.response.CreateAccountResponse;
import com.rogers.account.management.entities.Account;

import java.util.Date;
import java.util.List;


/**
 * The interface Account service.
 */
public interface AccountService {

    /**
     * Create account create account response.
     *
     * @param createAccountRequest the create account request
     * @return the create account response
     */
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

    /**
     * Gets account by email.
     *
     * @param emailId the email id
     * @return the account by email
     */
    Account getAccountByEmail(String emailId);

    /**
     * Gets account by account id and security pin.
     *
     * @param accountId   the account id
     * @param securityPIN the security pin
     * @return the account by account id and security pin
     */
    Account getAccountByAccountIdAndSecurityPIN(String accountId, String securityPIN);

    /**
     * Delete account.
     *
     * @param existingAccount the existing account
     */
    void deleteAccount(Account existingAccount);

    /**
     * Gets account by account id.
     *
     * @param accountId the account id
     * @return the account by account id
     */
    Account getAccountByAccountId(String accountId);

    /**
     * Update account.
     *
     * @param existingAccount the existing account
     * @param name            the name
     * @param email           the email
     * @param country         the country
     * @param postalCode      the postal code
     * @param age             the age
     * @param status          the status
     * @param updatedAt       the updated at
     */
    void updateAccount(Account existingAccount, String name, String email, String country, String postalCode, int age, String status, Date updatedAt);

    /**
     * Gets account.
     *
     * @param accountId the account id
     * @param email     the email
     * @return the account
     */
    Account getAccount(String accountId, String email);

    /**
     * Gets account count.
     *
     * @return the account count
     */
    List<AccountCountResponse> getAccountCount();
}
