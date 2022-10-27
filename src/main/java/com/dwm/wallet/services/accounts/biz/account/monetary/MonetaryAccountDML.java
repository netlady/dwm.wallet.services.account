package com.dwm.wallet.services.accounts.biz.account.monetary;

import com.dwm.wallet.services.accounts.biz.FunctionMultiOutput;
import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;

/**
 * insert/delete/update/select functions by their related business
 */

public class MonetaryAccountDML extends MonetaryAccountDataManipulate {

    /**
     * check validations and create a new monetary account for specific player
     *
     * @param playerId
     * @return : the status of creation
     */
    public MessageBundle createMonetaryAccount(int playerId) {
        /** ****get creation validation status for given player**** */
        MessageBundle createValidationStatus = validateCreation(playerId, AccountType.monetary);

        if (createValidationStatus != MessageBundle.validateSuccess) {
            return createValidationStatus;
        } else {
            return super.insertAccount(playerId);
        }
    }

    /**
     * validate and update balance of account
     *
     * @param accountId
     * @param transcAmount
     * @param transactionType
     * @return
     */
    public FunctionMultiOutput.outputByMonetaryAccount updateAccountBalance(int accountId, int transcAmount, TransactionType transactionType) {
        /** validate transaction on account */
        FunctionMultiOutput.outputByMonetaryAccount validateTransactionResult =
                validateTransaction(accountId, transcAmount, transactionType);

        if (validateTransactionResult.getOutputMessageBundle() != MessageBundle.validateSuccess) {
            return validateTransactionResult;
        }
        /** update account balance */
        validateTransactionResult.setOutputMessageBundle(super.updateBalance(validateTransactionResult.getOutputMonAccount(), transcAmount, transactionType));
        return validateTransactionResult;
    }

    /**
     * return balance of monetary account determined by membershipNo
     *
     * @param membershipNo
     * @return
     */
    public int getAccountBalanceByMembershipNo(String membershipNo) {
        /** find accountID */
        FunctionMultiOutput.outputByMonetaryAccount findAccountResult = getByMembershipNoAndAccountType(membershipNo, AccountType.monetary);

        if (findAccountResult.getOutputMessageBundle() != MessageBundle.validateSuccess) {
            return -1;
        }

        /** return balance */
        return findAccountResult.getOutputMonAccount().getAccountBalance();
    }

    public FunctionMultiOutput.outputByInteger getIdByMembershipNoAndAccountType(String membershipNo, AccountType accountType) {
        return super.getIdByMembershipNoAndAccountType(membershipNo, accountType);
    }
}