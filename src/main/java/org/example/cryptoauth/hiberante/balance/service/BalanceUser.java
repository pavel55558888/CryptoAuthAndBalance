package org.example.cryptoauth.hiberante.balance.service;

import org.example.cryptoauth.model.BalanceUserModel;
import org.example.cryptoauth.model.TransactionBalanceModel;
import org.example.cryptoauth.model.TypeTransaction;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.example.cryptoauth.model.dto.TransferUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface BalanceUser {
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BalanceUserModel getBalance(String username);
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BalanceUserModel setBalance(BalanceUserDTO balanceUserModel, TypeTransaction typeTransaction);
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public <T>T saleBalance(BalanceUserDTO balanceUserModel, TypeTransaction typeTransaction);
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public <T>T transferBalance(TransferUserDTO balanceUserModel);
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<TransactionBalanceModel> getTransactionBalance(String username);
}
