package Accounts;

import Accounts.Providers.AccountProvider;

import java.security.Provider;

public class Account {
    private Double balance;
    private String mobileNumber;
    private AccountProvider provider;

    Account(AccountProvider provider){
        this.provider = provider;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountProvider getProvider() {
        return provider;
    }

    public void setProvider(AccountProvider provider) {
        this.provider = provider;
    }
}
