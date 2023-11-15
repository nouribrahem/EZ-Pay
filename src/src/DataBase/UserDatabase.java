package DataBase;

import Accounts.Account;
import Accounts.BankAccount;
import UserManagement.User;

import java.util.List;

public class UserDatabase {
    private List<User> users;

    public boolean search(String userName) {
        return true;
    }

    public boolean searchSignin(String username, String password) {
        return true;
    }

    public boolean isRegisteredAccount(Account receivingAccount) {
        return true;
    }

    public User getUser(String username) {
        return users.get(0);
    }
}
