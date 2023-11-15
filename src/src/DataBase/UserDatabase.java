package DataBase;

import Accounts.*;
import Accounts.Providers.CIBBank;
import Accounts.Providers.CIBEwallet;
import UserManagement.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private final List<User> users;
    {
        users = new ArrayList<>();


    }

    public void fillData(){
        User temp  = new User();
        Account atemp = new BankAccount(new CIBBank());
        atemp.setBalance(1234.24);
        InstaPayAccount itemp = new InstaPayAccount(atemp);
        temp.setUserName("menna");
        temp.setPassword("123");
        temp.setInstaPayAccount(itemp);

        addUser(temp);
    }


    public boolean addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            System.out.println("User added successfully.");
            return true;
        } else {
            System.out.println("User already exists.");
            return false;
        }
    }

    public boolean search(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchSignin(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User isRegisteredAccount(Account receivingAccount) {
        for (User user : users) {
            InstaPayAccount instapayAccount = user.getInstaPayAccount();
            if (instapayAccount != null && instapayAccount.getAccount().equals(receivingAccount)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public void updateUserBalance(User user){
        for (User u : users) {
            if (u == user){
                Account account =  u.getInstaPayAccount().getAccount();
                account.setBalance(account.getProvider().getBalance());
                u.getInstaPayAccount().setAccount(account);
                break;
            }
        }
    }
}
