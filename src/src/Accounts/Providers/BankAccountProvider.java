package Accounts.Providers;

public class BankAccountProvider extends AccountProvider {
    @Override
    public Boolean verifyAccount() {
        return true;
    }
    @Override
    public Double getAccountBalance() {
        return null;
    }

}
