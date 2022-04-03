package dev.ufuk.bakan.account.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRequestDTO {
    @NotNull
    private Long customerNo;
    @NotEmpty
    private String ticket;

    public AccountRequestDTO(Long customerNo, String ticket) {
        this.customerNo = customerNo;
        this.ticket = ticket;
    }

    public Long getCustomerNo() {
        return this.customerNo;
    }

    public void setCustomerNo(Long customerNo) {
        this.customerNo = customerNo;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
