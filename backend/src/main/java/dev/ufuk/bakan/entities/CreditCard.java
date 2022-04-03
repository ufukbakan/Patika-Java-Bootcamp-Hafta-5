package dev.ufuk.bakan.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.ufuk.bakan.applications.Debt;

public class CreditCard implements Debt {
    @NotNull
    @JsonIgnore
    private Account owner;
    private double balance = 0.0; 
    // negative credit card balance = debt
    // positive credit card balance possible when refunds happen
    @NotEmpty
    private String cardNumber;

    public CreditCard(Account owner, String cardNumber) {
        this.owner = owner;
        this.cardNumber = cardNumber;
    }

    @Override
    public double getDebt() {
        return balance;
    }

    @Override
    public void payDebt(double amount) {
        if(owner.getBalance() >= amount){
            balance += amount; // this is the credit card's balance
            owner.setBalance(owner.getBalance() - amount); // this is the account's balance
        }
    }


    public Account getOwner() {
        return this.owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    

}
