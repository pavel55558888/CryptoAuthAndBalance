package org.example.cryptoauth.hiberante.balance.repository;

import org.example.cryptoauth.model.balance.dto.TransferUserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferBalance {
    public <T>T transferBalance(TransferUserDTO balanceUserModel);
}
