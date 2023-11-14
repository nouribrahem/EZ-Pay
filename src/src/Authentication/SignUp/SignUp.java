package Authentication.SignUp;

import Accounts.Providers.Provider;
import DataBase.DatabaseManager;
import UserManagement.User;
import UserManagement.UserAction;

import java.util.Objects;
import java.util.Scanner;

public abstract class SignUp {
    OTPManager otp;
    User user = new User();

    public SignUp(OTPManager otp) {
        this.otp = otp;
    }

    public boolean signUp() {
        return createAccount() && getUserData();
    }

    public abstract boolean createAccount();

    public boolean getUserData() {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the user Name you want:");
        String userName = in.nextLine();
        if(!DatabaseManager.search(userName)){
            System.out.println("valid username");
            user.setUserName(userName);
            System.out.println("Please enter the password:");
            String password = in.nextLine();
            if(password.matches("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$")){
                System.out.println("Valid password");
                user.setPassword(password);
//                UserAction.displayUser();
                return true;
            }
            else{
                System.out.println("The password must contain numbers, letters and special characters.");
            }
        }
        else{
            System.out.println("This User name is already exists before.");

        }
        return false;
    }

    public User getSignupUser(){
        return user;
    }



}