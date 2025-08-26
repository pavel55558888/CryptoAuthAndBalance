package org.example.cryptoauth.hiberante.balance.repository;

import org.example.cryptoauth.model.BalanceUserModel;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.example.cryptoauth.model.dto.TransferUserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferBalance {
    public <T>T transferBalance(TransferUserDTO balanceUserModel);
}
