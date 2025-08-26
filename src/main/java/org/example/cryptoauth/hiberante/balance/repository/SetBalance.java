package org.example.cryptoauth.hiberante.balance.repository;

import org.example.cryptoauth.model.BalanceUserModel;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface SetBalance {
    public BalanceUserModel setBalance(BalanceUserDTO balanceUserModel);
}
