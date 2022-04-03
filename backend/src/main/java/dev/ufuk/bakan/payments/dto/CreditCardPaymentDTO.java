package dev.ufuk.bakan.payments.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreditCardPaymentDTO {
    @NotNull
    private long customerNo;
    @NotEmpty
    private String ticket;
    @NotEmpty
    private String cardNumber;
    @Positive
    private long amount;

    public CreditCardPaymentDTO(long customerNo, String ticket, String cardNumber, long amount) {
        this.customerNo = customerNo;
        this.ticket = ticket;
        this.cardNumber = cardNumber;
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

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


}
