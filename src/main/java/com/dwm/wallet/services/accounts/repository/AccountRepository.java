package com.dwm.wallet.services.accounts.repository;

import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for account Entity
 */

public interface AccountRepository extends JpaRepository<Account, Integer> {
    /**
     * check if there is any record by given data
     * @param playerId
     * @param accountType
     * @return
     */
    boolean existsByPlayerIdAndAccountType(int playerId, AccountType accountType);

    /**
     * return account by given data
     * @param playerId
     * @param accountType
     * @return
     */
    Account findByPlayerIdAndAccountType(int playerId, AccountType accountType);
}