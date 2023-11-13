package UserManagement;

import Accounts.InstaPayAccount;

public class User {
    private String userName;
    private String password;
    private InstaPayAccount instaPayAccount;

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

    public InstaPayAccount getInstaPayAccount() {
        return instaPayAccount;
    }

    public void setInstaPayAccount(InstaPayAccount instaPayAccount) {
        this.instaPayAccount = instaPayAccount;
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}
