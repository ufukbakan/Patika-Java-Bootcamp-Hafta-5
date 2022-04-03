package dev.ufuk.bakan.account.dto;

import dev.ufuk.bakan.entities.Account;

public class AccountSummaryDTO {
    private String name;
    private long customerNo;
    private double balance;
    private double totalDebt;

    public AccountSummaryDTO(Account account){
        this.name = account.getName();
        this.customerNo = account.getCustomerNo();
        this.balance = account.getBalance();
        this.totalDebt = account.getTotalDebt();
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCustomerNo() {
        return this.customerNo;
    }

    public void setCustomerNo(long customerNo) {
        this.customerNo = customerNo;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalDebt() {
        return this.totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

}
