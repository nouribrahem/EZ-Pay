package Accounts;

import java.security.Provider;

public class BankAccount extends Account{
    private String bankNumber;

    public void setBankNumber(String bankNumber){
        this.bankNumber = bankNumber;
    }

    BankAccount(Provider provider) {
        super(provider);
    }
}
