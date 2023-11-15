package Accounts.Providers;

import Accounts.EwalletAccount;

import java.util.Random;

public class EwalletAccountProvider extends AccountProvider {

    @Override
    public void fillAccounts() {
        double balance = 1000.0;
        for(int i = 0;i < 10;i++){
            Random random = new Random();
            long phone = random.nextLong(999999999);
            EwalletAccount account = new EwalletAccount(this);
            account.setBalance(balance+(100*i));
            account.setMobileNumber("01"+String.valueOf(phone));
            registeredAccounts.add(account);
        }
    }
}
