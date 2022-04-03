package dev.ufuk.bakan.applications.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ApplicationDTO {
    @NotNull
    private long customerNo;
    @NotEmpty
    private String ticket;
    @Positive
    private double amount;

    public ApplicationDTO(long customerNo, String ticket, double amount) {
        this.customerNo = customerNo;
        this.ticket = ticket;
        this.amount = amount;
    }


    public long getCustomerNo() {
        return this.customerNo;
    }

    public void setCustomerNo(long customerNo) {
        this.customerNo = customerNo;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
