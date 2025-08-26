package org.example.cryptoauth.hiberante.balance.repository;

import org.example.cryptoauth.model.balance.dto.BalanceUserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleBalance {
    public <T>T saleBalance(BalanceUserDTO balanceUserModel);
}
