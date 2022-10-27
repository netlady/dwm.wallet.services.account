package com.dwm.wallet.services.accounts.biz.transaction;

import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;
import com.dwm.wallet.services.accounts.model.MonetaryAccount;
import com.dwm.wallet.services.accounts.model.Transaction;

/**
 * abstract class for inserting account's transactions logs
 * outer-packages must reach these functions by extends classes
 */
abstract class TransactionDataManipulate extends TransactionDataFetch {

    /**
     * insert a transaction log for new updating player's monetary balance
     * @param monAccount
     * @param transAmount
     * @param transactionType
     * @return
     */
    protected MessageBundle insertMonetaryTransaction(MonetaryAccount monAccount, int transAmount, TransactionType transactionType) {
        int prevAccountBalance = monAccount.getAccountBalance();
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(transAmount);
        transaction.setTransactionType(transactionType);
        transaction.setAccountId(monAccount.getAccountId());
        transaction.setPreTransAccountBalance(prevAccountBalance);
        transaction.setTransactionDate();
        transcRepo.save(transaction);
        return MessageBundle.saveSuccess;
    }
}