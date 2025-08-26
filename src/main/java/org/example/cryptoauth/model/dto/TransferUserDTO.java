package org.example.cryptoauth.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TransferUserDTO {
    @NotBlank(message = "Продавец не может быть пустым")
    private String usernameSeller;
    @NotBlank(message = "Покупаетль не может быть пустым")
    private String usernameBuyer;
    @Min(value = 100, message = "Минимальное пополнение/продажа/перевод от 100 рублей")
    private double value;

    public TransferUserDTO() {}

    public String getUsernameSeller() {
        return usernameSeller;
    }

    public void setUsernameSeller(String usernameSeller) {
        this.usernameSeller = usernameSeller;
    }

    public String getUsernameBuyer() {
        return usernameBuyer;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
