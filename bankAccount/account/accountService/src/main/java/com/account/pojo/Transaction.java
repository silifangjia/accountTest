package com.account.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;


public class Transaction {
    private Date tradTime;

    private char tradType;

    private double tradAmount;

    private double balance;

    Transaction(char tradType, double tradAmount, double balance) {
        tradTime = new Date();

        this.tradType = tradType;

        this.tradAmount = tradAmount;

        this.balance = balance;
    }

    public Date getTradTime() {
        return tradTime;

    }

    public char getTradType() {
        return tradType;

    }

    public double getTradAmount() {
        return tradAmount;

    }

    public double getBalance() {
        return balance;

    }

    public String toString() {
        return getTradTime() + "\t" + getTradType() + "\t\t" + getTradAmount() + "\t" + balance;

    }

}
