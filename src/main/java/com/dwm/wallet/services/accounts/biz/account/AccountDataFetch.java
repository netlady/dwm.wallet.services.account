package com.dwm.wallet.services.accounts.biz.account;

import com.dwm.wallet.services.accounts.biz.CallPlayerService;
import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * interface class for functions which have allowance to select account's data
 * outer-packages must reach these functions by implemented classes
 */

abstract class AccountDataFetch {
    @Autowired
    AccountRepository accRepo;

    /**
     * validate if is it possible to create account for given player
     * @param playerId
     * @param accountType
     * @return
     */
    protected MessageBundle validateCreation(int playerId, AccountType accountType) {
        /** ****check if playerId and accountType is not created before ****
         * instead of entity constraint, we use this validation to prevent of:
         *     wasting Identity increasing during insertion
         *     or
         *     calling other costly functions
         */
        if (accRepo.existsByPlayerIdAndAccountType(playerId, accountType)) {
            return MessageBundle.validateFail_DuplicateAccount;
        }

        /**  ****validate existence of player**** */
        if (!new CallPlayerService().IsPlayerExistence(playerId)) {
            return MessageBundle.validateFail_UnknownPlayer;
        }
        return MessageBundle.validateSuccess;
    }
}