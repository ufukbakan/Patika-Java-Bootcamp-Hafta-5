package dev.ufuk.bakan.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.ufuk.bakan.applications.Debt;

public class Credit implements Debt{
    @NotNull
    @JsonIgnore
    private Account owner;
    @Positive
    private double amount;
    @Positive
    private double paidBack = 0;
    private static long creditIdCounter = 0;
    private long id;


    public Credit(Account owner, double amount){
        this.id = creditIdCounter++;
        this.owner = owner;
        this.amount = amount;
        owner.setBalance(owner.getBalance() + amount);
    }

    @Override
    public double getDebt() {
        return (amount - paidBack);
    }

    @Override
    public void payDebt(double amount) {
        if(this.amount - paidBack > 0 && owner.getBalance() >= amount && amount <= (this.amount - paidBack)){
            paidBack += amount;
            owner.setBalance(owner.getBalance() - amount);
        }else if(this.amount - paidBack > 0 && owner.getBalance() >= amount){
            amount = (this.amount - paidBack); // dont allow paying more than remaining ammount
            paidBack += amount;
            owner.setBalance(owner.getBalance() - amount);
        }

    }


    public Account getOwner() {
        return this.owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPaidBack() {
        return this.paidBack;
    }

    public void setPaidBack(double paidBack) {
        this.paidBack = paidBack;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
}
