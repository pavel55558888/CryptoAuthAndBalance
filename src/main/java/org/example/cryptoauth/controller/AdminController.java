package org.example.cryptoauth.controller;

import org.example.cryptoauth.hiberante.balance.repository.GetTransactionBalance;
import org.example.cryptoauth.model.TransactionBalanceModel;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    GetTransactionBalance getTransactionBalance;
    @GetMapping("/balance/{username}")
    public List<TransactionBalanceModel> balance(@PathVariable String username) {
        return getTransactionBalance.getTransactionBalance(username);
    }
}
