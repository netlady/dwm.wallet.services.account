package com.dwm.wallet.services.accounts.biz.transaction;

import com.dwm.wallet.services.accounts.biz.FunctionMultiOutput;
import com.dwm.wallet.services.accounts.biz.account.monetary.MonetaryAccountDML;
import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;
import com.dwm.wallet.services.accounts.model.Transaction;

import java.util.List;

/**
 * All main logic functions for transaction which may other classes need are here
 */

public class TransactionDML extends TransactionDataManipulate {

    /**
     * return all transactions of specific player defined by its membershipNo
     * @param membershipNo
     * @param accountType
     * @return
     */
    public List<Transaction> getAllTransactionsByMembershipNo(String membershipNo, AccountType accountType)
    {
        /** find accountID */
        FunctionMultiOutput.outputByInteger findAccountResult =
                getIdByMembershipNoAndAccountType(membershipNo, accountType);

        if(findAccountResult.getOutputMessageBundle() != MessageBundle.validateSuccess)
        {
            return null;
        }
        /** return all related transactions */
        return super.getAllTransactions(findAccountResult.getOutputInteger());
    }

    /**
     * update account balance by setting new transaction
     * @param MembershipNo
     * @param TransAmount
     * @param transactionType
     * @return
     */
    public MessageBundle setMonetaryTransaction(String MembershipNo, int TransAmount, TransactionType transactionType)
    {
        /** ****validate account and return if there is any**** */
        FunctionMultiOutput.outputByInteger findAccountIdStatus = getIdByMembershipNoAndAccountType(MembershipNo, AccountType.monetary);

        if(findAccountIdStatus.getOutputMessageBundle() != MessageBundle.validateSuccess) {
            return findAccountIdStatus.getOutputMessageBundle();
        }

        /** ****validate and update related monetary account**** */
        FunctionMultiOutput.outputByMonetaryAccount accountUpdateResult = updateAccountBalance(findAccountIdStatus.getOutputInteger(), TransAmount, transactionType);

        if(accountUpdateResult.getOutputMessageBundle() != MessageBundle.saveSuccess)
        {
            return accountUpdateResult.getOutputMessageBundle();
        }

        /** ****insert transaction**** */
        return super.insertMonetaryTransaction(accountUpdateResult.getOutputMonAccount(), TransAmount, transactionType);
    }
}