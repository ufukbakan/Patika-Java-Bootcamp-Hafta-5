package dev.ufuk.bakan.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ufuk.bakan.login.dto.LoginPersonDTO;
import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.authentication.Authenticator;
import dev.ufuk.bakan.entities.*;

@Service
public class LoginService {

    @Autowired
    Authenticator authenticator;

    @Autowired
    AccountService accountService;

    public long login(LoginPersonDTO loginDTO){
        Account found =  accountService.getAccountByIdAndPassword(loginDTO.getId(), loginDTO.getPassword());
        if(found != null){
            authenticator.setTicket(found.getCustomerNo(), loginDTO.getTicket());
            return found.getCustomerNo();
        }
        return -1;
    }
}
