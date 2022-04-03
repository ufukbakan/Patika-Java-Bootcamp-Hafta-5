package dev.ufuk.bakan.account.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransferMoneyDTO {
    @NotNull(message = "Customer no can not be empty")
    private Long fromCustomerNo;
    @NotNull(message = "Customer ticket can not be empty")
    private String fromCustomerTicket;
    @NotNull(message = "Customer no can not be empty")
    private Long toCustomerNo;
    @NotNull(message = "Amount can not be empty")
    @Positive(message = "Amount must be positive")
    private Double amount;

    public TransferMoneyDTO(Long fromCustomerNo, String fromCustomerTicket, Long toCustomerNo, Double amount) {
        this.fromCustomerNo = fromCustomerNo;
        this.fromCustomerTicket = fromCustomerTicket;
        this.toCustomerNo = toCustomerNo;
        this.amount = amount;
    }    


    public long getFromCustomerNo() {
        return this.fromCustomerNo;
    }

    public void setFromCustomerNo(long fromCustomerNo) {
        this.fromCustomerNo = fromCustomerNo;
    }

    public String getFromCustomerTicket() {
        return this.fromCustomerTicket;
    }

    public void setFromCustomerTicket(String fromCustomerTicket) {
        this.fromCustomerTicket = fromCustomerTicket;
    }

    public long getToCustomerNo() {
        return this.toCustomerNo;
    }

    public void setToCustomerNo(long toCustomerNo) {
        this.toCustomerNo = toCustomerNo;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
