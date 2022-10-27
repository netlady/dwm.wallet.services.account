package com.dwm.wallet.services.accounts.biz.account;

import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;

/**
 * class to define account functions which allow to manipulate data
 * outer-packages must reach these functions by implemented classes
 */
interface AccountDataManipulate {

    /**
     * insert account depends on its type
     * @param playerId
     * @return
     */
    MessageBundle insertAccount(int playerId);
}