package Accounts.Providers;

public abstract class AccountProvider {
    public abstract Boolean verifyAccount();
    public abstract Double getAccountBalance();
    public static void main(String[] args) {
        System.out.println("Hello world!");
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
