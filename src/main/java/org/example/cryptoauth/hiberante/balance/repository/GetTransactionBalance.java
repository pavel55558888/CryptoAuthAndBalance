package org.example.cryptoauth.hiberante.balance.repository;

import org.example.cryptoauth.model.TransactionBalanceModel;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetTransactionBalance {
    public List<TransactionBalanceModel> getTransactionBalance(String username);
}
