package Accounts.Providers;

import Accounts.Account;
import Accounts.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BankAccountProvider extends AccountProvider {
    private static List<BankAccount>BankRegisteredAccounts = new ArrayList<BankAccount>();


    public static List<BankAccount> getBankRegisteredAccounts() {
        return BankRegisteredAccounts;
    }

    public static void setBankRegisteredAccounts(List<BankAccount> bankRegisteredAccounts) {
        BankRegisteredAccounts = bankRegisteredAccounts;
    }

    @Override
    public Account search(Account account) {
        for(BankAccount a:BankRegisteredAccounts){
            if( ((BankAccount)account).getBankNumber().equals(a.getBankNumber())){
                return a;
            }


        }
        return null;
    }

    @Override
    public void fillAccounts() {
        BankAccount a1 = new BankAccount(new CIBBank());
        a1.setBalance(1000.0);
        a1.setMobileNumber("01550091354");
        a1.setBankNumber("12345678");
        this.BankRegisteredAccounts.add(a1);

        BankAccount a2 = new BankAccount(new CIBBank());
        a2.setBalance(1200.0);
        a2.setMobileNumber("01008935245");
        a2.setBankNumber("87654321");
        this.BankRegisteredAccounts.add(a2);
//        double balance = 1000.0;
//        for(int i = 0;i < 10;i++){
//            Random random = new Random();
//            long phone = random.nextLong(999999999);
//            BankAccount account = new BankAccount(this);
//            account.setBalance(balance+(100*i));
//            account.setMobileNumber("01"+String.valueOf(phone));
//            account.setBankNumber(String.valueOf(random.nextLong(99999999)));
//            registeredAccounts.add(account);
        }

    @Override
    public Boolean updateAccountBalance(Account account, double amount) {
        if(verifyAccount(account)){
            for(BankAccount a : BankRegisteredAccounts){
                if(Objects.equals(((BankAccount) account).getBankNumber(), a.getBankNumber())){
                    System.out.println("Amount is " + amount);
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
        Account target = BankRegisteredAccounts.get(BankRegisteredAccounts.indexOf(account));
        return target.getBalance() >= transactionAmount;
    }
//    }


//    @Override
//    public Boolean verifyAccount(Account account) {
//        BankAccount bank = new BankAccount(account.getProvider());
//        return registeredAccounts.stream().anyMatch(account.getBankNumber().equals(account.getBankNumber()));
//    }
}
