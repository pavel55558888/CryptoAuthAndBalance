package org.example.cryptoauth.model.balance.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BalanceUserDTO {
    @NotBlank(message = "Пользователь не может быть пустым")
    private String username;
    @Min(value = 100, message = "Минимальное пополнение/продажа/перевод от 100 рублей")
    private double value;
    public BalanceUserDTO() {}

    public BalanceUserDTO(String username, double value) {
        this.username = username;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
