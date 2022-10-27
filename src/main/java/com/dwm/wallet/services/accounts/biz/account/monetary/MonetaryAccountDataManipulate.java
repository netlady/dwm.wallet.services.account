package com.dwm.wallet.services.accounts.biz.account.monetary;

import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;
import com.dwm.wallet.services.accounts.model.MonetaryAccount;

import java.util.Date;
import java.util.Random;

/**
 * abstract class for functions which have allowance to manipulate account's data
 * outer-packages must reach these functions by inheritance classes
 * note: all validations of each function must be called before these functions on logical part
 */

abstract class MonetaryAccountDataManipulate extends MonetaryAccountDataFetch{

    /**
     * insert a new account for player
     * @param playerId
     * @return
     */
    @Override
    public MessageBundle insertAccount(int playerId) {
        MonetaryAccount monAccount = new MonetaryAccount();
        /** properties of main entity (account) */
        monAccount.setAccountType(AccountType.monetary);
        monAccount.setAccountActivateDate();
        monAccount.setPlayerId(playerId);
        /** properties of monetary entity */
        monAccount.setAccountBalance(0);
        monAccount.setAccountNo(String.format("M%dA%03d", playerId, new Random().nextInt(999)));
        /** save */
        monAccountRepo.save(monAccount);
        return MessageBundle.saveSuccess;
    }

    /**
     * increase/decrease the balance of account
     * @param monAccount
     * @param amount
     * @param transactionType
     * @return
     */
    protected MessageBundle updateBalance(MonetaryAccount monAccount, int amount, TransactionType transactionType)
    {
        if(transactionType == TransactionType.deposit)
        {
            monAccount.setAccountBalance(monAccount.getAccountBalance() + amount);
        }
        else //withdraw
        {
            monAccount.setAccountBalance(monAccount.getAccountBalance() - amount);
        }
        monAccount.setAccountLastTransactionDate(new Date());
        monAccountRepo.save(monAccount);
        return MessageBundle.saveSuccess;
    }
}