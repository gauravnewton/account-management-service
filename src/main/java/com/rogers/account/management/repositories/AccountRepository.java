/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.repositories;

import com.rogers.account.management.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Account repository.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    /**
     * Find by email account.
     *
     * @param emailId the email id
     * @return the account
     */
    Account findByEmail(String emailId);

    /**
     * Find by account id and security pin account.
     *
     * @param accountId   the account id
     * @param securityPIN the security pin
     * @return the account
     */
    Account findByAccountIdAndSecurityPIN(String accountId, String securityPIN);

    /**
     * Find by account id account.
     *
     * @param accountId the account id
     * @return the account
     */
    Account findByAccountId(String accountId);

    /**
     * Find by account id and email account.
     *
     * @param accountId the account id
     * @param email     the email
     * @return the account
     */
    Account findByAccountIdAndEmail(String accountId, String email);
}
