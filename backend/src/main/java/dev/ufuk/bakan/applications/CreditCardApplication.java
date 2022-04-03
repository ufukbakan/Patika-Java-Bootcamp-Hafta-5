package dev.ufuk.bakan.applications;

import java.time.LocalDate;

import dev.ufuk.bakan.entities.Account;
import dev.ufuk.bakan.entities.CreditCard;

public class CreditCardApplication extends Application<CreditCard> {

    private String cardNumber;
    private static long cardCounter = 0;

    public CreditCardApplication(Account applicant) {
        this.applicant = applicant;
        this.cardNumber = generateCardNumber();
    }

    private String generateCardNumber(){
        return String.format("%04d-%04d-%04d", cardCounter++, applicant.getCustomerNo(), LocalDate.now().getYear());
    }

    public Account getApplicant() {
        return this.applicant;
    }

    public void setApplicant(Account applicant) {
        this.applicant = applicant;
    }

    @Override
    public CreditCard apply() {
        return new CreditCard(applicant, cardNumber);
    }
    
}
