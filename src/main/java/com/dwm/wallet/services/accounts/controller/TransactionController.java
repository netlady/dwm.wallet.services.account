package com.dwm.wallet.services.accounts.controller;

import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;
import com.dwm.wallet.services.accounts.biz.transaction.TransactionDML;
import com.dwm.wallet.services.accounts.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * transaction controller to make some account transaction's functions accessible for other services/apps
 * main logic of each function is written in super class
 * as each player can have multi accounts :
 *       by membershipNo/playerId we are unable to access to specific account's transactions
 */
/**not completed class:
 *       Problem by its solution
////////////////////must change function and parameters: Suggestion, used new class for pass the parameters
    ///////////////as @RequestBody => (accountId/accountNo, amount, TransactionType)
 */
@RestController
public class TransactionController extends TransactionDML {

    /**
     * decrease balance of monetary account related by given membershipNo
     * @param membershipNo
     * @return
     */
    @PostMapping("monAccount/withdrawal/{membershipNo}/{transactionAmount}")
    public MessageBundle withdrawalMonetaryAccount(@PathVariable String membershipNo, @PathVariable int transactionAmount) {
        return super.setMonetaryTransaction(membershipNo, transactionAmount, TransactionType.withdrawal);
    }

    /**
     * increase the balance of monetary account related by given membershipNo
     * @param membershipNo
     * @return
     */
    @PostMapping("monAccount/deposit/{membershipNo}/{transactionAmount}")
    public MessageBundle depositMonetaryAccount(@PathVariable String membershipNo, @PathVariable int transactionAmount) {
        return super.setMonetaryTransaction(membershipNo, transactionAmount, TransactionType.deposit);
    }

    /**
     * return all Monetary transactions related by membership
     * @param membershipNo
     * @return
     */
    @GetMapping("monAccount/transactions/{membershipNo}")
    public List<Transaction> GetAllMonetaryTransactions(@PathVariable String membershipNo) {
        return super.getAllTransactionsByMembershipNo(membershipNo, AccountType.monetary);
    }
}