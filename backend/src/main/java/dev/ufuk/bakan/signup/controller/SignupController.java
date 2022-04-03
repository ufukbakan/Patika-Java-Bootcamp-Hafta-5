package dev.ufuk.bakan.signup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.signup.dto.SignupAccountDTO;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<String> signup(@Valid @RequestBody SignupAccountDTO signupAccountDTO){
        return accountService.addAccount(signupAccountDTO);
    }

}
