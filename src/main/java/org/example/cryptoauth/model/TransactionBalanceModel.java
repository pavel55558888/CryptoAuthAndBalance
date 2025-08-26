package org.example.cryptoauth.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transaction_balance")
public class TransactionBalanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private BalanceUserModel balanceUser;
    private Timestamp timestamp;
    private String typeTransaction;
    private double value;

    public TransactionBalanceModel() {
    }

    public TransactionBalanceModel(BalanceUserModel balanceUser, TypeTransaction typeTransaction, double value) {
        this.balanceUser = balanceUser;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.typeTransaction = typeTransaction.toString();
        this.value = value;
    }

    public BalanceUserModel getBalanceUser() {
        return balanceUser;
    }

    public void setBalanceUser(BalanceUserModel balanceUser) {
        this.balanceUser = balanceUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction.toString();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
