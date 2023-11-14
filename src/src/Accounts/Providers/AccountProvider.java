package Accounts.Providers;

public abstract class AccountProvider {
    public abstract Boolean verifyAccount();
    public abstract Double getAccountBalance();
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}
