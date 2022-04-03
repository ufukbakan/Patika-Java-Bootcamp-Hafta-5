package dev.ufuk.bakan.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.authentication.Authenticator;
import dev.ufuk.bakan.entities.Credit;
import dev.ufuk.bakan.entities.CreditCard;
import dev.ufuk.bakan.payments.dto.CreditCardPaymentDTO;
import dev.ufuk.bakan.payments.dto.CreditPaymentDTO;

@Service
public class PaymentService {

    @Autowired
    AccountService accountService;

    @Autowired
    Authenticator authenticator;
    
    public ResponseEntity<String> payCreditCardDebt(CreditCardPaymentDTO creditCardPaymentDTO){
        authenticator.authenticate(creditCardPaymentDTO.getCustomerNo(), creditCardPaymentDTO.getTicket());
        List<CreditCard> cards = accountService.getCreditCardsByCustomerNo(creditCardPaymentDTO.getCustomerNo());
        CreditCard card = cards.stream().filter(c -> c.getCardNumber().equals(creditCardPaymentDTO.getCardNumber())).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card is not found"));
        if(card.getOwner().getBalance() < creditCardPaymentDTO.getAmount()){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Balance is not enough to pay debt");
        }
        else if(card.getDebt() >= 0){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "No debt to pay");
        }
        else{
            card.payDebt(creditCardPaymentDTO.getAmount());
            return new ResponseEntity<String>("Payment Successfull", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> payCreditDebt(CreditPaymentDTO creditPaymentDTO){
        authenticator.authenticate(creditPaymentDTO.getCustomerNo(), creditPaymentDTO.getTicket());
        List<Credit> credits = accountService.getCreditsByCustomerNo(creditPaymentDTO.getCustomerNo());
        Credit credit = credits.stream().filter(c -> c.getId() == creditPaymentDTO.getCreditId() ).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit is not found"));
        if(credit.getOwner().getBalance() < creditPaymentDTO.getAmount()){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Balance is not enough to pay debt");
        }
        else if(credit.getDebt() <= 0){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "No debt to pay");
        }
        else{
            credit.payDebt(creditPaymentDTO.getAmount());
            return new ResponseEntity<String>("Payment Successfull", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> spendFromCreditCard(CreditCardPaymentDTO creditCardPaymentDTO){
        authenticator.authenticate(creditCardPaymentDTO.getCustomerNo(), creditCardPaymentDTO.getTicket());
        List<CreditCard> cards = accountService.getCreditCardsByCustomerNo(creditCardPaymentDTO.getCustomerNo());
        CreditCard card = cards.stream().filter(c -> c.getCardNumber().equals(creditCardPaymentDTO.getCardNumber())).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card is not found"));
        card.setBalance(card.getBalance() - creditCardPaymentDTO.getAmount());
        return new ResponseEntity<String>("Spending Successfull", HttpStatus.OK);
    }
}
