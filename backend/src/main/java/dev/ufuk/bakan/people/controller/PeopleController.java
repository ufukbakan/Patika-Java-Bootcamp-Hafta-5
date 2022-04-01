package dev.ufuk.bakan.people.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev.ufuk.bakan.entities.Person;
import dev.ufuk.bakan.people.dto.SignupPersonDTO;
import dev.ufuk.bakan.people.service.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService signupService;

    @PostMapping
    public void signup(@Valid @RequestBody SignupPersonDTO signupPersonDTO){
        signupService.addPerson(signupPersonDTO);
    }

    @GetMapping
    public Person getPerson(@RequestParam long customerNo){
        return signupService.getPersonByCustomerNo(customerNo);
    }

}
