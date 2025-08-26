package org.example.cryptoauth.model.balance;

import jakarta.persistence.*;

@Entity
@Table(name = "BalanceUser")
public class BalanceUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private double value;

    public BalanceUserModel(String username, double value) {
        this.username = username;
        this.value = value;
    }
    public BalanceUserModel() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
