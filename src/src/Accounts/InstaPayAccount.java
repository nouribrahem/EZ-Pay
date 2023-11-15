package Accounts;

public class InstaPayAccount{
    private Account account;
    public InstaPayAccount(Account account){
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
