package Accounts.Providers;

public abstract class EwalletAccountProvider extends AccountProvider {
    @Override
    public abstract Boolean verifyAccount() ;
    @Override
    public abstract Double getAccountBalance();
}
