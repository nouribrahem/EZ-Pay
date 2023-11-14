package Accounts;

import Accounts.Providers.AccountProvider;

import java.security.Provider;

public class BankAccount extends Account{
    private String bankNumber;
    public BankAccount(AccountProvider provider) {
        super(provider);
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber){
        this.bankNumber = bankNumber;
    }

}
