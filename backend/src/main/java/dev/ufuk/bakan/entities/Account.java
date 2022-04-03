package dev.ufuk.bakan.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import dev.ufuk.bakan.applications.Debt;
import dev.ufuk.bakan.signup.dto.SignupAccountDTO;

public class Account {
    private static long customerCounter;
    @NotNull
    private long customerNo;
    @NotEmpty
    @Length(min = 5, max = 255)
    private String name;
    @NotEmpty
    @Length(min = 9, max = 11)
    private String id;
    @NotEmpty
    @Length(min = 6, max = 255)
    private String password;
    private double balance = 0.0;
    private List<CreditCard> creditCards = new ArrayList<>();
    private List<Credit> credits = new ArrayList<>();

    public Account(){}

    public Account(String name, String id, String password){
        this.name = name;
        this.id = id;
        this.password = password;
        this.customerNo = customerCounter++;
    }

    public Account(SignupAccountDTO registerPersonDTO){
        this.name = registerPersonDTO.getName();
        this.id = registerPersonDTO.getId();
        this.password = registerPersonDTO.getPassword();
        this.customerNo = customerCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(long customerNo) {
        this.customerNo = customerNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person [customerNo=" + customerNo + ", id=" + id + ", name=" + name + "]";
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public List<CreditCard> getCreditCards() {
        return this.creditCards;
    }

    public List<Credit> getCredits() {
        return this.credits;
    }

    public double getTotalDebt(){
        double total = 0;
        for(Debt debt: creditCards){
            total -= debt.getDebt();
        }
        for(Debt debt: credits){
            total += debt.getDebt();
        }
        return total;
    }


}
