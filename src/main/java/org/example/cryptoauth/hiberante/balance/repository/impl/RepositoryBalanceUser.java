package org.example.cryptoauth.hiberante.balance.repository.impl;

import org.example.cryptoauth.hiberante.balance.repository.*;
import org.example.cryptoauth.hiberante.balance.service.BalanceUser;
import org.example.cryptoauth.model.balance.BalanceUserModel;
import org.example.cryptoauth.model.balance.TransactionBalanceModel;
import org.example.cryptoauth.model.balance.dto.BalanceUserDTO;
import org.example.cryptoauth.model.balance.dto.TransferUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RepositoryBalanceUser implements GetBalance, SetBalance, SaleBalance, TransferBalance, GetTransactionBalance {
    @Autowired
    BalanceUser balanceUser;

    @Override
    public BalanceUserModel getBalance(String username) {
        return balanceUser.getBalance(username);
    }

    @Override
    public BalanceUserModel setBalance(BalanceUserDTO balanceUserModel) {
        return balanceUser.setBalance(balanceUserModel, null);
    }

    @Override
    public <T>T saleBalance(BalanceUserDTO balanceUserModel) {
        return balanceUser.saleBalance(balanceUserModel, null);
    }

    @Override
    public <T>T transferBalance(TransferUserDTO balanceUserModel) {
        return balanceUser.transferBalance(balanceUserModel);
    }

    @Override
    public List<TransactionBalanceModel> getTransactionBalance(String username) {
        return balanceUser.getTransactionBalance(username);
    }
}
