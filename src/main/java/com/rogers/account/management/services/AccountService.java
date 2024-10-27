package com.rogers.account.management.services;

import com.rogers.account.management.dtos.request.CreateAccountRequest;
import com.rogers.account.management.dtos.response.AccountCountResponse;
import com.rogers.account.management.dtos.response.CreateAccountResponse;
import com.rogers.account.management.entities.Account;

import java.util.Date;
import java.util.List;


public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

    Account getAccountByEmail(String emailId);

    Account getAccountByAccountIdAndSecurityPIN(String accountId, String securityPIN);

    void deleteAccount(Account existingAccount);

    Account getAccountByAccountId(String accountId);

    void updateAccount(Account existingAccount, String name, String email, String country, String postalCode, int age, String status, Date updatedAt);

    Account getAccount(String accountId, String email);

    List<AccountCountResponse> getAccountCount();
}
