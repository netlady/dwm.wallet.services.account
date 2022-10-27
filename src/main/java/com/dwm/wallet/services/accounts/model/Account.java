package com.dwm.wallet.services.accounts.model;

import com.dwm.wallet.services.accounts.biz.staticValues.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Account entity structure,
 * it's a basic entity of accounts which player can have in the system
 * However, each player can have different account types, but each type per player must be unique.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"playerId", "accountType"})})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue
    private int accountId;
    private int playerId;
    private AccountType accountType;
    private Date accountActivateDate;
    private Date accountDeactivateDate;

    public void setAccountActivateDate() {
        this.accountActivateDate = new Date();
    }
}