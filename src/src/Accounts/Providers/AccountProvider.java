package Accounts.Providers;

import Accounts.Account;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountProvider {
    protected List<Account> registeredAccounts = new ArrayList<Account>();

    public List<Account> getRegisteredAccounts() {
        return registeredAccounts;
    }

    public void setRegisteredAccounts(List<Account> registeredAccounts) {
        this.registeredAccounts = registeredAccounts;
    }

    public abstract void fillAccounts();
    public abstract Boolean verifyAccount(Account account);
    public  Double getAccountBalance(Account account){
        if(verifyAccount(account)){
            Account target = registeredAccounts.get(registeredAccounts.indexOf(account));
            return target.getBalance();
        }
        else{
            return null;
        }
    }
    public Boolean updateAccountBalance(Account account,double amount) {
        if(verifyAccount(account)){
            Account target = registeredAccounts.get(registeredAccounts.indexOf(account));
            target.setBalance(target.getBalance()+amount);
            return true;
        }
        //simple update in list
        //might need account tho to search the list for it
        return false;
    }
    public boolean hasSufficientBalance(Account account, double transactionAmount){
        if(verifyAccount(account)){
            Account target = registeredAccounts.get(registeredAccounts.indexOf(account));
            return target.getBalance() >= transactionAmount;
        }
        return false;
    }

    public Boolean updateAccountBalance(double amount) {
        //simple update in list
        //might need account tho to search the list for it
        return true;
    }
    public double getBalance(){
        //search list for the account
        //then get its balance
        //this method will be called after update account balance to ensure it's updated
        return 1.2;
    }
}
