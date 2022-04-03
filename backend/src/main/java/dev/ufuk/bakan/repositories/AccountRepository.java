package dev.ufuk.bakan.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ufuk.bakan.entities.Account;

@Component
public class AccountRepository {
    private List<Account> accountList = new ArrayList<>();

    public Account retrieveAccountByCustomerNo(long customerNo) {
        return accountList.stream().filter(p -> p.getCustomerNo() == customerNo).findFirst().orElseGet(() -> null);
    }

    public boolean doesExistById(String id) {
        return accountList.stream().
        filter(p -> p.getId().equals(id)).
        findFirst().isPresent();
    }

    public boolean doesExistByCustomerNo(long customerNo) {
        return accountList.stream().
        filter(p -> p.getCustomerNo() == customerNo).
        findFirst().isPresent();
    }

    public Account retrieveAccountByIdAndPassword(String id, String password) {
        return accountList.stream().
        filter(p -> p.getId().equals(id) && p.getPassword().equals(password)).
        findFirst().orElseGet(() -> null);
    }

    public void save(Account person) {
        accountList.add(person);
    }

    public List<Account> retrieveAll() {
        return accountList;
    }

}
