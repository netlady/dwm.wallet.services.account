package com.dwm.wallet.services.accounts.biz.transaction;

import com.dwm.wallet.services.accounts.biz.account.monetary.MonetaryAccountDML;
import com.dwm.wallet.services.accounts.model.Transaction;
import com.dwm.wallet.services.accounts.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/***
 * class for transaction functions which are allowed to select data
 * outer-packages must reach these functions by extends classes
 */

abstract class TransactionDataFetch extends MonetaryAccountDML {

    @Autowired
    protected TransactionRepository transcRepo;

    /**
     * return all transactions of given accountId
     * @param accountId
     * @return
     */
    protected List<Transaction> getAllTransactions(int accountId)
    {
        return transcRepo.findAllByAccountId(accountId);
    }
}