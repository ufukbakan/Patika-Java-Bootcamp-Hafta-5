package dev.ufuk.bakan.signup.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SignupAccountDTO {
    @NotEmpty(message = "Name can not be empty")
    @Length(min = 5, max = 255, message = "Name length must be in interval [5-255]")
    private String name;
    @NotEmpty(message = "Id can not be empty")
    @Length(min = 9, max = 11, message = "ID length must be in interval [9-11]")
    private String id;
    @NotEmpty(message = "Password can not be empty")
    @Length(min = 6, max = 255, message = "Password length must be in interval [6-255]")
    private String password;
    @NotNull(message =  "Birthdate can not be empty")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate birthdate;

    public SignupAccountDTO(String name, String id, String password, LocalDate birthdate) {
        if(LocalDate.now().minusYears(18).isBefore(birthdate)){
            throw new ResponseStatusException(HttpStatus.SEE_OTHER,"You must be at least 18 years old");
        }
        this.name = name;
        this.id = id;
        this.password = password;
        this.birthdate = birthdate;
    }    

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

    public void setPassword(String password){
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

}
