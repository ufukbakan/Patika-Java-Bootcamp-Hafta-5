package dev.ufuk.bakan.login.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import dev.ufuk.bakan.login.dto.LoginPersonDTO;
import dev.ufuk.bakan.login.service.LoginService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public long login(@RequestBody LoginPersonDTO loginDTO){
        return loginService.login(loginDTO);
    }
}
