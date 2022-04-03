package dev.ufuk.bakan.applications;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ufuk.bakan.account.dto.AccountRequestDTO;
import dev.ufuk.bakan.applications.dto.ApplicationDTO;
import dev.ufuk.bakan.applications.service.ApplicationService;

@RestController
@RequestMapping("/apply")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;
    
    @PostMapping("/credit")
    public ResponseEntity<String> applyForCredit(@RequestBody @Valid ApplicationDTO applicationDTO){
        applicationService.applyForCredit(applicationDTO);
        return new ResponseEntity<String>("Application successfull", HttpStatus.OK);
    }

    @PostMapping("/creditCard")
    public ResponseEntity<String> applyForCreditCard(@RequestBody @Valid AccountRequestDTO accountRequestDTO){
        applicationService.applyForCreditCard(accountRequestDTO);
        return new ResponseEntity<String>("Application successfull", HttpStatus.OK);
    }
}
