package org.example.cryptoauth.hiberante.balance.repository;

import org.example.cryptoauth.model.balance.TransactionBalanceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetTransactionBalance {
    public List<TransactionBalanceModel> getTransactionBalance(String username);
}
