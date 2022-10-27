package com.dwm.wallet.services.accounts.controller;

import com.dwm.wallet.services.accounts.biz.account.monetary.MonetaryAccountDML;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import org.springframework.web.bind.annotation.*;

/**
 * account controller to make some account's functions accessible for other services/apps
 * main logic of each function is written in super class
 */

@RestController
public class AccountController extends MonetaryAccountDML {

    /**
     * create default type of account for specific new player
     * @param playerId
     * @return : if account is created return true otherwise false
     */
    @PostMapping("/account/open/def/{playerId}")
    public boolean createDefaultAccount(@PathVariable int playerId) {
        /** ****In this project default account is a monetary account**** */
        if (super.createMonetaryAccount(playerId) == MessageBundle.saveSuccess) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * return account balance related by given membershipNo
     * @param membershipNo
     * @return
     */
    @GetMapping("monAccount/balance/{membershipNo}")
    public int getAccountBalanceByMembershipNo(@PathVariable String membershipNo) {
        return super.getAccountBalanceByMembershipNo(membershipNo);
    }
}