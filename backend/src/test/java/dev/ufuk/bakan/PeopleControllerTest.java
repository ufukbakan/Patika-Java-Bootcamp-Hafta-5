package dev.ufuk.bakan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.ufuk.bakan.people.controller.PeopleController;
import dev.ufuk.bakan.people.dto.SignupPersonDTO;
import dev.ufuk.bakan.people.service.PeopleService;

@SpringBootTest
public class PeopleControllerTest {
    
    @Autowired
    private PeopleController controller;
    @Autowired
    private PeopleService service;

    @Test
    public void contextLoad(){
        assertNotNull(controller);
    }

    @Test
    public void signupTest(){
        SignupPersonDTO pdto1 = new SignupPersonDTO(), pdto2 = new SignupPersonDTO();
        pdto1.setName("Ufuk Bakan");
        pdto2.setName("Hasan Bakan");

        controller.signup(pdto1);
        controller.signup(pdto2);
        
        assertEquals(2, service.getAllPeople().size());
    }
}
