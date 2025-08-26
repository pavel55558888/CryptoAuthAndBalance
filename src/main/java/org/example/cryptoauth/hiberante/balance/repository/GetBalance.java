package org.example.cryptoauth.hiberante.balance.repository;


import org.example.cryptoauth.model.BalanceUserModel;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GetBalance {
    public BalanceUserModel getBalance(String username);
}
