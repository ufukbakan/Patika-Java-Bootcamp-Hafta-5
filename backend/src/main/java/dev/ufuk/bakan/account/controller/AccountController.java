package dev.ufuk.bakan.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ufuk.bakan.account.dto.AccountRequestDTO;
import dev.ufuk.bakan.account.dto.AccountSummaryDTO;
import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.entities.Credit;
import dev.ufuk.bakan.entities.CreditCard;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    
    @PostMapping("/summary")
    public AccountSummaryDTO summary(@RequestBody @Valid AccountRequestDTO accountRequestDTO ){
        return accountService.getAccountSummary(accountRequestDTO);
    }

    @PostMapping("/details/creditCards")
    public List<CreditCard> creditCards(@RequestBody @Valid AccountRequestDTO accountRequestDTO){
        return accountService.getCreditCards(accountRequestDTO);
    }

    @PostMapping("/details/credits")
    public List<Credit> credits(@RequestBody @Valid AccountRequestDTO accountRequestDTO){
        return accountService.getCredits(accountRequestDTO);
    }
}
