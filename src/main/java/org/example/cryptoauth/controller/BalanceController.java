package org.example.cryptoauth.controller;

import jakarta.validation.Valid;
import org.example.cryptoauth.hiberante.balance.repository.GetBalance;
import org.example.cryptoauth.hiberante.balance.repository.SaleBalance;
import org.example.cryptoauth.hiberante.balance.repository.SetBalance;
import org.example.cryptoauth.hiberante.balance.repository.TransferBalance;
import org.example.cryptoauth.model.BalanceUserModel;
import org.example.cryptoauth.model.dto.BalanceUserDTO;
import org.example.cryptoauth.model.dto.TransferUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {
    @Autowired
    private GetBalance getBalance;
    @Autowired
    private SetBalance setBalance;
    @Autowired
    private SaleBalance saleBalance;
    @Autowired
    private TransferBalance transferBalance;

    @GetMapping("/balance/{username}")
    public ResponseEntity<?> getBalance(@PathVariable String username) {
        BalanceUserModel balanceUserModel = getBalance.getBalance(username);
        if (!balanceUserModel.getUsername().equals(username)) {
            return ResponseEntity.badRequest().body("Пользователь " + username + " не найден. Пополните баланс");
        }
        return ResponseEntity.ok().body(balanceUserModel);
    }

    @PostMapping("/balance")
    public ResponseEntity<?> setBalance(@RequestBody @Valid BalanceUserDTO balanceUserModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        return ResponseEntity.ok().body(setBalance.setBalance(balanceUserModel));
    }

    @PutMapping("/balance")
    public ResponseEntity<?> saleBalance(@RequestBody @Valid BalanceUserDTO balanceUserModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        return ResponseEntity.ok().body(saleBalance.saleBalance(balanceUserModel));
    }
    @PutMapping("/balance/{usernameRecipient}")
    public ResponseEntity<?> transferBalance(@RequestBody @Valid TransferUserDTO balanceUserModel, BindingResult bindingResult, @PathVariable String usernameRecipient) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        return ResponseEntity.ok().body(transferBalance.transferBalance(balanceUserModel));
    }

}
