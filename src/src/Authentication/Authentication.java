package Authentication;
import Authentication.SignUp.SignUp;

import DataBase.DatabaseManager;
import UserManagement.User;
import UserManagement.UserAction;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Authentication {
    private SignUp signUp;
    private User user;


    public boolean signIn(String username, String password){
        System.out.println("Please enter your username");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        user.setUserName(username);

        System.out.println("Please enter you password");
        password = scanner.nextLine();
        user.setPassword(password);

        if(DatabaseManager.searchSignin(username, password)){
            System.out.println("Login successful");
            user = getSigninUser();
            return true;
        }else{
            System.out.println("User name or password incorrect");
            return false;
        }

    }
    public boolean signUp(){
        if(signUp.signUp()){
            user = signUp.getSignupUser();
            return true;
        }
        else {return false;}
    }

    public User getSigninUser(){
        return user;
    }



}
