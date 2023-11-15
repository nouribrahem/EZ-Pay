package Authentication;
import Authentication.SignUp.SignUp;

import DataBase.UserDatabase;
import UserManagement.User;
import java.util.Scanner;

public class Authentication {
    private SignUp signUp;
    private User user;

    public boolean signIn(String username, String password){
        UserDatabase userDatabase = new UserDatabase();
        user.setUserName(username);
        user.setPassword(password);

        if(userDatabase.searchSignin(username, password)){
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
