package org.example.cryptoauth.hiberante.balance.service.impl;

import jakarta.persistence.EntityManager;
import org.example.cryptoauth.hiberante.balance.service.BalanceUser;
import org.example.cryptoauth.model.BalanceUserModel;
import org.example.cryptoauth.model.TransactionBalanceModel;
import org.example.cryptoauth.model.TypeTransaction;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.example.cryptoauth.model.dto.TransferUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceUserImpl  implements BalanceUser {
    @Autowired
    EntityManager entityManager;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public BalanceUserModel getBalance(String username) {
        log.info("Get balance " + username);
        return getBalanceUserModelUsernameFromTable(username);
    }

    @Override
    public BalanceUserModel setBalance(BalanceUserDTO balanceUser, TypeTransaction typeTransaction) {
        log.info("Set balance " + balanceUser);
        BalanceUserModel balanceUserModel = getBalanceUserModelUsernameFromTable(balanceUser.getUsername());
        if(!balanceUserModel.getUsername().equals(balanceUser.getUsername())) {
            balanceUserModel.setUsername(balanceUser.getUsername());
            balanceUserModel.setValue(balanceUser.getValue());
            log.info("New record balance user " + balanceUser.getUsername());
            entityManager.persist(balanceUserModel);
        }else{
            balanceUserModel.setValue(balanceUser.getValue() + balanceUserModel.getValue());
            log.info("Updated balance user " + balanceUser.getUsername());
            entityManager.merge(balanceUserModel);
        }

        log.info("Insert transaction balance user " + balanceUser.getUsername());
        entityManager.persist(new TransactionBalanceModel(balanceUserModel, typeTransaction != null ? typeTransaction : TypeTransaction.BUY, balanceUser.getValue()));

        return getBalanceUserModelUsernameFromTable(balanceUser.getUsername());
    }

    @Override
    public <T>T saleBalance(BalanceUserDTO balanceUser, TypeTransaction typeTransaction) {
        log.info("Sale balance " + balanceUser.getValue());
        BalanceUserModel balanceUserModel = getBalanceUserModelUsernameFromTable(balanceUser.getUsername());

        if (balanceUserModel.getUsername().contains("not found")) {
            return (T) balanceUserModel.getUsername();
        }if (balanceUser.getValue() > balanceUserModel.getValue()) {
            return (T) "Баланс пользователя меньше, чем сумма запроса в заявке на вывод";
        }
        balanceUserModel.setValue(balanceUserModel.getValue() - balanceUser.getValue());
        log.info("Updated balance user " + balanceUser.getUsername() + " " + balanceUser.getValue());
        entityManager.merge(balanceUserModel);

        log.info("Insert transaction balance user " + balanceUser.getUsername());
        entityManager.persist(new TransactionBalanceModel(balanceUserModel, typeTransaction != null ? typeTransaction : TypeTransaction.SELL, balanceUser.getValue()));

        return (T) balanceUserModel;
    }

    @Override
    public <T>T transferBalance(TransferUserDTO balanceUser) {
        log.info("Transfer balance " + balanceUser);

        Object balanceUserSale = saleBalance(new BalanceUserDTO(balanceUser.getUsernameSeller(), balanceUser.getValue()), TypeTransaction.TRANSFER);

        if (balanceUserSale instanceof BalanceUserModel) {
            BalanceUserModel balanceUserSet =  setBalance(new BalanceUserDTO(balanceUser.getUsernameBuyer(), balanceUser.getValue()), TypeTransaction.TRANSFER);
            return (T) List.of(balanceUserSale, balanceUserSet);
        }else{
            return (T) balanceUserSale;
        }
    }

    @Override
    public List<TransactionBalanceModel> getTransactionBalance(String username) {

        return entityManager
                .createQuery("FROM TransactionBalanceModel WHERE balanceUser.username = :param1", TransactionBalanceModel.class)
                .setParameter("param1", username)
                .getResultList();
    }

    public BalanceUserModel getBalanceUserModelUsernameFromTable(String username) {
        List<BalanceUserModel> balanceUserList = entityManager.createQuery("from BalanceUserModel where username = :param1", BalanceUserModel.class)
                .setParameter("param1", username)
                .getResultList();

        if (balanceUserList.isEmpty()) {
            log.info("User \"" + username + "\" not found");
            return new BalanceUserModel("User " + username + " not found", 0);
        }

        return balanceUserList.get(0);
    }
}
