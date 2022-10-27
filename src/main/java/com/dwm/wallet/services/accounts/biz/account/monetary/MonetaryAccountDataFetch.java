package com.dwm.wallet.services.accounts.biz.account.monetary;

import com.dwm.wallet.services.accounts.biz.CallPlayerService;
import com.dwm.wallet.services.accounts.biz.FunctionMultiOutput;
import com.dwm.wallet.services.accounts.biz.account.AccountDML;
import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;
import com.dwm.wallet.services.accounts.model.MonetaryAccount;
import com.dwm.wallet.services.accounts.repository.MonetaryAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * interface class for functions which have allowance to select monetary account's data
 * outer-packages must reach these functions by extends classes
 */

abstract class MonetaryAccountDataFetch extends AccountDML {
    @Autowired
    MonetaryAccountRepository monAccountRepo;

    /**
     * return monetary account if there is any regards specific data
     * @param membershipNo
     * @param accountType
     * @return
     */
    protected FunctionMultiOutput.outputByMonetaryAccount getByMembershipNoAndAccountType(String membershipNo, AccountType accountType) {
        FunctionMultiOutput funMultOut = new FunctionMultiOutput();
        FunctionMultiOutput.outputByMonetaryAccount accountStatus = funMultOut.new outputByMonetaryAccount();
        accountStatus.setOutputMonAccount(null);

        /** ****find playerId**** */
        int playerId = new CallPlayerService().getPlayerId(membershipNo);

        if(playerId == -1)
        {
            accountStatus.setOutputMessageBundle(MessageBundle.validateFail_UnknownPlayer);
            return accountStatus;
        }

        /** ****find related account**** */
        MonetaryAccount monAccount = monAccountRepo.findAccountByPlayerIdAndAccountType(playerId, AccountType.monetary);

        if(monAccount == null)
        {
            accountStatus.setOutputMessageBundle(MessageBundle.validateFail_UnknownAccount);
            return accountStatus;
        }

        accountStatus.setOutputMessageBundle(MessageBundle.validateSuccess);
        accountStatus.setOutputMonAccount(monAccount);

        return accountStatus;
    }

    /**
     * return monetary account id if there is any regards specific data
     * @param membershipNo
     * @param accountType
     * @return
     */
    protected FunctionMultiOutput.outputByInteger getIdByMembershipNoAndAccountType(String membershipNo, AccountType accountType) {
        FunctionMultiOutput funMultOut = new FunctionMultiOutput();
        FunctionMultiOutput.outputByInteger accountIdStatus = funMultOut.new outputByInteger();
        accountIdStatus.setOutputInteger(-1);

        FunctionMultiOutput.outputByMonetaryAccount monAccountStatus = getByMembershipNoAndAccountType(membershipNo, accountType);
        accountIdStatus.setOutputMessageBundle(monAccountStatus.getOutputMessageBundle());
        if(monAccountStatus.getOutputMessageBundle() == MessageBundle.validateSuccess)
        {
            accountIdStatus.setOutputInteger(monAccountStatus.getOutputMonAccount().getAccountId());
        }

        return accountIdStatus;
    }

    /**
     * check if transaction is valid for specific account
     * @param accountId
     * @param transcAmount
     * @return
     */
    protected FunctionMultiOutput.outputByMonetaryAccount validateTransaction(int accountId, int transcAmount, TransactionType transactionType)
    {
        FunctionMultiOutput funMultOut = new FunctionMultiOutput();
        FunctionMultiOutput.outputByMonetaryAccount result = funMultOut.new outputByMonetaryAccount();
        result.setOutputMonAccount(null);
        /** check amount validation status */
        if(transcAmount <= 0)
        {
            result.setOutputMessageBundle(MessageBundle.validateFail_UnValidRequestedAmount);
            return result;
        }
        /** check if account exist */
        MonetaryAccount monAccount = monAccountRepo.findByAccountId(accountId);
        if(monAccount == null)
        {
            result.setOutputMessageBundle(MessageBundle.validateFail_UnknownAccount);
        }
        //
        result.setOutputMonAccount(monAccount);

        /** check if account doesn't expire / closed */
        if(monAccount.getAccountDeactivateDate() != null) {
            result.setOutputMessageBundle(MessageBundle.validateFail_AccountClosed);
            return result;
        }
        /** check if account has balance to decrease */
        if(transactionType == TransactionType.withdrawal) {
            int currentBalance = monAccount.getAccountBalance();
            if (currentBalance <= 0 || currentBalance - transcAmount < 0) {
                result.setOutputMessageBundle(MessageBundle.validateFail_InsufficientBalance);
                return result;
            }
        }
        result.setOutputMessageBundle(MessageBundle.validateSuccess);
        return result;
    }
}