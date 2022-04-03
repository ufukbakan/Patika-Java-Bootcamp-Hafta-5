package dev.ufuk.bakan.account.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ufuk.bakan.account.dto.AccountRequestDTO;
import dev.ufuk.bakan.account.dto.AccountSummaryDTO;
import dev.ufuk.bakan.account.dto.TransferMoneyDTO;
import dev.ufuk.bakan.authentication.Authenticator;
import dev.ufuk.bakan.entities.Account;
import dev.ufuk.bakan.entities.Credit;
import dev.ufuk.bakan.entities.CreditCard;
import dev.ufuk.bakan.repositories.AccountRepository;
import dev.ufuk.bakan.signup.dto.SignupAccountDTO;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Authenticator authenticator;

    public Account getAccountByIdAndPassword(String id, String password){
        return accountRepository.retrieveAccountByIdAndPassword(id, password);
    }

    public boolean doesAccountExistsByCustomerNo(long customerNo){
        return accountRepository.doesExistByCustomerNo(customerNo);
    }

    public ResponseEntity<String> addAccount(SignupAccountDTO signupAccountDTO){
        if(! accountRepository.doesExistById(signupAccountDTO.getId())){
            if(isPasswordValid(signupAccountDTO)){
                accountRepository.save(new Account(signupAccountDTO));
                return new ResponseEntity<String>("Successfully signed up", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Password should not contain birthdate", HttpStatus.BAD_REQUEST);
            }
        }
        else
            return new ResponseEntity<String>("An account already exists with that ID", HttpStatus.CONFLICT);
    }

    private boolean isPasswordValid(SignupAccountDTO dto){
        List<String> bannedStrings = new ArrayList<>();
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("yyyyMMdd")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("ddMMyyyy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("ddMMyy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yy")) );
        bannedStrings.add( dto.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yy")) );
        for(String bannedString: bannedStrings){
            if(dto.getPassword().contains(bannedString))
                return false;
        }
        return true;
    }

    private <T> void printList(List<T> list){
        for(T t: list){
            System.out.println(t.toString());
        }
    }

    public List<Account> getAllAccounts(){
        return accountRepository.retrieveAll();
    }

    public Account getAccountByCustomerNo(long customerNo){
        return accountRepository.retrieveAccountByCustomerNo(customerNo);
    }

    public ResponseEntity<String> transferMoney(TransferMoneyDTO transferMoneyDTO ){
        authenticator.authenticate(transferMoneyDTO.getFromCustomerNo(), transferMoneyDTO.getFromCustomerTicket());
        Account sender = getAccountByCustomerNo(transferMoneyDTO.getFromCustomerNo());
        Account receiver = getAccountByCustomerNo(transferMoneyDTO.getToCustomerNo());
        if(receiver == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiver doesn't exist");
        }
        if(sender.getBalance() < transferMoneyDTO.getAmount()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance is not enough to complete transfer");
        }
        sender.setBalance(sender.getBalance()-transferMoneyDTO.getAmount());
        receiver.setBalance(receiver.getBalance()+transferMoneyDTO.getAmount());
        return new ResponseEntity<String>("Payment confirmed", HttpStatus.OK);
    }

    public AccountSummaryDTO getAccountSummary(AccountRequestDTO accountRequestDTO){
        authenticator.authenticate(accountRequestDTO.getCustomerNo(), accountRequestDTO.getTicket());
        Account account = getAccountByCustomerNo(accountRequestDTO.getCustomerNo());
        return new AccountSummaryDTO(account);
    }

    public List<CreditCard> getCreditCards(AccountRequestDTO accountRequestDTO){
        authenticator.authenticate(accountRequestDTO.getCustomerNo(), accountRequestDTO.getTicket());
        Account account = getAccountByCustomerNo(accountRequestDTO.getCustomerNo());
        return account.getCreditCards();
    }

    public List<CreditCard> getCreditCardsByCustomerNo(long customerNo){
        Account account = getAccountByCustomerNo(customerNo);
        return account.getCreditCards();
    }

    public List<Credit> getCredits(AccountRequestDTO accountRequestDTO){
        authenticator.authenticate(accountRequestDTO.getCustomerNo(), accountRequestDTO.getTicket());
        Account account = getAccountByCustomerNo(accountRequestDTO.getCustomerNo());
        return account.getCredits();
    }

    public List<Credit> getCreditsByCustomerNo(long customerNo){
        Account account = getAccountByCustomerNo(customerNo);
        return account.getCredits();
    }
}
