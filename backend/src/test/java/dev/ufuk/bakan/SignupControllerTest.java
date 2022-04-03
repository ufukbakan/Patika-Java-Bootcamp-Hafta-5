package dev.ufuk.bakan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.signup.controller.SignupController;
import dev.ufuk.bakan.signup.dto.SignupAccountDTO;

@SpringBootTest
public class SignupControllerTest {
    
    @Autowired
    private SignupController controller;
    @Autowired
    private AccountService service;

    @Test
    public void contextLoad(){
        assertNotNull(controller);
    }

    /*
    @Test
    public void signupTest(){
        SignupAccountDTO pdto1 = new SignupAccountDTO(), pdto2 = new SignupAccountDTO();
        pdto1.setName("Ufuk Bakan");
        pdto1.setId("0123456789");
        pdto2.setName("Hasan Bakan");
        pdto1.setId("01234567890");

        controller.signup(pdto1);
        controller.signup(pdto2);
        
        assertEquals(2, service.getAllAccounts().size());
    }*/
}
