package com.rogers.account.management.repositories;

import com.rogers.account.management.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String emailId);

    Account findByAccountIdAndSecurityPIN(String accountId, String securityPIN);

    Account findByAccountId(String accountId);

    Account findByAccountIdAndEmail(String accountId, String email);
}
