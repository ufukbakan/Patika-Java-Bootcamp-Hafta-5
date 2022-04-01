package dev.ufuk.bakan.people.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class SignupPersonDTO {
    @NotEmpty
    @Length(min = 5, max = 255)
    private String name;
    @NotEmpty
    @Length(min = 9, max = 11)
    private String id;
    @NotEmpty
    @Length(min = 6, max = 255)
    private String password;

    public SignupPersonDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
