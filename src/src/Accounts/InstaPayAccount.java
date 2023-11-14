package Accounts;

public class InstaPayAccount {
    private Account account;
    private String instaPayAccountNumber;
    InstaPayAccount(Account account, String instaPayAccountNumber){
        this.account = account;
        this.instaPayAccountNumber = instaPayAccountNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getInstaPayAccountNumber() {
        return instaPayAccountNumber;
    }

    public void setInstaPayAccountNumber(String instaPayAccountNumber) {
        this.instaPayAccountNumber = instaPayAccountNumber;
    }
}
