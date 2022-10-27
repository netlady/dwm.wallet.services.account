package com.dwm.wallet.services.accounts.repository;

import com.dwm.wallet.services.accounts.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for transaction Entity
 **/

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByAccountId(int accountId);
}