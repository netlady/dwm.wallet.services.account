package com.dwm.wallet.services.accounts.repository;

import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.model.MonetaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for monetary-account Entity
 */

public interface MonetaryAccountRepository extends JpaRepository<MonetaryAccount, Integer> {
    /**
     * find monetary-account record by its accountId
     * @param accountId
     * @return
     */
    MonetaryAccount findByAccountId(int accountId);

    /**
     * find monetary-account record by its playerId and accountType
     * use accountType in inputs to be sure of its correctness
     * @param playerId
     * @param accountType
     * @return
     */
    MonetaryAccount findAccountByPlayerIdAndAccountType(int playerId, AccountType accountType);
}