package Accounts.Providers;

import Accounts.BankAccount;

import java.util.Random;

public class BankAccountProvider extends AccountProvider {

    @Override
    public void fillAccounts() {
        double balance = 1000.0;
        for(int i = 0;i < 10;i++){
            Random random = new Random();
            long phone = random.nextLong(999999999);
            BankAccount account = new BankAccount(this);
            account.setBalance(balance+(100*i));
            account.setMobileNumber("01"+String.valueOf(phone));
            account.setBankNumber(String.valueOf(random.nextLong(99999999)));
            registeredAccounts.add(account);
        }
    }
}
