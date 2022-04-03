package dev.ufuk.bakan.applications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ufuk.bakan.account.dto.AccountRequestDTO;
import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.applications.CreditApplication;
import dev.ufuk.bakan.applications.CreditCardApplication;
import dev.ufuk.bakan.applications.dto.ApplicationDTO;
import dev.ufuk.bakan.authentication.Authenticator;
import dev.ufuk.bakan.entities.Account;

@Service
public class ApplicationService {

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private AccountService accountService;

    public void applyForCredit(ApplicationDTO applicationDTO){
        authenticator.authenticate(applicationDTO.getCustomerNo(), applicationDTO.getTicket());
        Account owner = accountService.getAccountByCustomerNo(applicationDTO.getCustomerNo());
        owner.getCredits().add(
            new CreditApplication(owner, applicationDTO.getAmount()).apply()
        );
    }

    public void applyForCreditCard(AccountRequestDTO accountRequestDTO){
        authenticator.authenticate(accountRequestDTO.getCustomerNo(), accountRequestDTO.getTicket());
        Account owner = accountService.getAccountByCustomerNo(accountRequestDTO.getCustomerNo());
        owner.getCreditCards().add(
            new CreditCardApplication(owner).apply()
        );
    }


}
