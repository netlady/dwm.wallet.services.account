package com.dwm.wallet.services.accounts.biz.account;

import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;

/**
 * class to access the account functions which may need by other classes
 */

public class AccountDML extends AccountDataFetch implements AccountDataManipulate  {

    @Override
    public MessageBundle insertAccount(int playerId) {
        return null;
    }
}