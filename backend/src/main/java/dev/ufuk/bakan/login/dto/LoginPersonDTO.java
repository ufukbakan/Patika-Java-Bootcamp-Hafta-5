package dev.ufuk.bakan.login.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoginPersonDTO {
    @NotEmpty
    @Length(min = 9, max = 11)
    private String id;
    @NotEmpty
    @Length(min = 6, max = 255)
    private String password;
    @NotEmpty
    private String ticket;

    public LoginPersonDTO(String id, String password, String ticket) {
        this.id = id;
        this.password = password;
        this.ticket = ticket;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }


}
