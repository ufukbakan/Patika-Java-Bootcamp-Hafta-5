package dev.ufuk.bakan.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import dev.ufuk.bakan.people.dto.SignupPersonDTO;

public class Person {
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

    public Person(){}

    public Person(String name, String id, String password){
        this.name = name;
        this.id = id;
        this.password = password;
        this.customerNo = customerCounter++;
    }

    public Person(SignupPersonDTO registerPersonDTO){
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

}
