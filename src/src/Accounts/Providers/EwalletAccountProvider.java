package Accounts.Providers;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.EwalletAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EwalletAccountProvider extends AccountProvider {
    private static List<EwalletAccount> EwalletRegisteredAccounts = new ArrayList<EwalletAccount>();
    @Override
    public Account search(Account account) {
        for(EwalletAccount a:EwalletRegisteredAccounts){
            if(Objects.equals(((EwalletAccount) account).getMobileNumber(), a.getMobileNumber())){
                return a;
            }
        }
        return null;
    }

    public static List<EwalletAccount> getEwalletRegisteredAccounts() {
        return EwalletRegisteredAccounts;
    }

    public static void setEwalletRegisteredAccounts(List<EwalletAccount> ewalletRegisteredAccounts) {
        EwalletRegisteredAccounts = ewalletRegisteredAccounts;
    }

    @Override
    public void fillAccounts() {
        EwalletAccount a1 = new EwalletAccount(new CIBEwallet());
        a1.setBalance(100.0);
        a1.setMobileNumber("01234545678");
        this.EwalletRegisteredAccounts.add(a1);
        EwalletAccount a2 = new EwalletAccount(new FawryEwallet());
        a2.setMobileNumber("01253537363");
        a2.setBalance(2000.0);
        this.EwalletRegisteredAccounts.add(a2);
    }
    public Boolean updateAccountBalance(Account account, double amount) {
        if(verifyAccount(account)){
            for(EwalletAccount a : EwalletRegisteredAccounts){
                if(Objects.equals(account.getMobileNumber(), a.getMobileNumber())){
                    a.setBalance(a.getBalance()+amount);
                    break;
                }
            }
            return true;
        }
        return false;

    }

    @Override
    public boolean hasSufficientBalance(Account account, double transactionAmount) {
        Account target = EwalletRegisteredAccounts.get(EwalletRegisteredAccounts.indexOf(account));
        return target.getBalance() >= transactionAmount;
    }

}
