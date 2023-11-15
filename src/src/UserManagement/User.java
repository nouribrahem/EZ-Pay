package UserManagement;

import Accounts.Account;
import Accounts.InstaPayAccount;

public class User {
    private String userName;
    private String password;
    private InstaPayAccount instaPayAccountaccount;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InstaPayAccount getAccount() {
        return instaPayAccountaccount;
    }

    public void setInstaPayAccountaccount(InstaPayAccount account) {
        this.instaPayAccountaccount = account;
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}
