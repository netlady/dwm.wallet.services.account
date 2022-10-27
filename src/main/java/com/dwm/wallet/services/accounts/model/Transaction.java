package com.dwm.wallet.services.accounts.model;

import com.dwm.wallet.services.accounts.biz.staticValues.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Transaction Entity structure
 * every debit and credit of each account will be recorded by this struct
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private int transactionId;
    private int accountId;
    private int preTransAccountBalance;
    private TransactionType transactionType;
    private int TransactionAmount;
    private Date TransactionDate;

    public void setTransactionDate() {
        this.TransactionDate = new Date();
    }
}