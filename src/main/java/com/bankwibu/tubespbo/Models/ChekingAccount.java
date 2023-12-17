package com.bankwibu.tubespbo.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ChekingAccount extends Account {
    // The Number of transactions a client is allowed to do per day,
    private final IntegerProperty transactionLimit;

    public ChekingAccount(String owner, String accountNumber, double balance, int tlimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", tlimit);
    }

    public IntegerProperty transactionLimitProperty() {return transactionLimit;}
}