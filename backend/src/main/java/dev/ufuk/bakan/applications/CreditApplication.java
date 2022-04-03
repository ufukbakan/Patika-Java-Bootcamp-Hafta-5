package dev.ufuk.bakan.applications;

import dev.ufuk.bakan.entities.Account;
import dev.ufuk.bakan.entities.Credit;

public class CreditApplication extends Application<Credit> {

    private double amount;

    public CreditApplication(Account applicant, double amount){
        this.applicant = applicant;
        this.amount = amount;
    }
    public Credit apply(){
        return new Credit(applicant, amount);
    }

}
