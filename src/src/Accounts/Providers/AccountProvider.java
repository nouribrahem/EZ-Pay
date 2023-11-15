package Accounts.Providers;

import Accounts.Account;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountProvider {




    public abstract Account search(Account account);
    public abstract void fillAccounts();
    public  Boolean verifyAccount(Account account) {
        if(search(account)!=null){
            return true;
        }
        return false;
    }
    public  Double getAccountBalance(Account account){
        Account a = search(account);
        if (a != null){
            return a.getBalance();
        }
        return null;
    }
    public abstract Boolean updateAccountBalance(Account account,double amount);
    public abstract boolean hasSufficientBalance(Account account, double transactionAmount);


//    public double getBalance(Account account){
//        //search list for the account
//        //then get its balance
//        //this method will be called after update account balance to ensure it's updated
//        return 1.2;
//    }
}
