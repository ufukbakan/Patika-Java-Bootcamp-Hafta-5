package dev.ufuk.bakan.payments.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ufuk.bakan.account.dto.TransferMoneyDTO;
import dev.ufuk.bakan.account.service.AccountService;
import dev.ufuk.bakan.payments.dto.CreditCardPaymentDTO;
import dev.ufuk.bakan.payments.dto.CreditPaymentDTO;
import dev.ufuk.bakan.payments.service.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/send")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferMoneyDTO transferMoneyDTO){
        return accountService.transferMoney(transferMoneyDTO);
    }

    @PostMapping(value = "/creditCard")
    public ResponseEntity<String> payCreditCardDebt(@Valid @RequestBody CreditCardPaymentDTO creditCardPaymentDTO){
        return paymentService.payCreditCardDebt(creditCardPaymentDTO);
    }

    @PostMapping(value = "/spend")
    public ResponseEntity<String> spendCC(@Valid @RequestBody CreditCardPaymentDTO creditCardPaymentDTO){
        return paymentService.spendFromCreditCard(creditCardPaymentDTO);
    }

    @PostMapping(value = "/credit")
    public ResponseEntity<String> payCreditDebt(@Valid @RequestBody CreditPaymentDTO creditPaymentDTO){
        return paymentService.payCreditDebt(creditPaymentDTO);
    }
}
