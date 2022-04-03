package dev.ufuk.bakan.payments.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreditPaymentDTO {
    @NotNull
    private long customerNo;
    @NotEmpty
    private String ticket;
    @NotNull
    private long creditId;
    @Positive
    private long amount;

    public CreditPaymentDTO(long customerNo, String ticket, long creditId, long amount) {
        this.customerNo = customerNo;
        this.ticket = ticket;
        this.creditId = creditId;
        this.amount = amount;
    }

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
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

    public long getCreditId() {
        return this.creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }


}
