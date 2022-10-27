package com.dwm.wallet.services.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Monetary account entity structure,
 * Each player just can have 1 monetary account,
 * For rapid fetch last date of transaction will be updated beside balance updating
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonetaryAccount extends Account {
    @Column(unique = true)
    private String accountNo;
    private int accountBalance;
    private Date accountLastTransactionDate;
}