package UserManagement;

import DataBase.DatabaseManager;

import java.util.Scanner;

public class UserAction {
    private static User currentUser;
    private DatabaseManager databaseManager;

    //userSignOptions
    public void displaySignMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to EZ-Pay!\n1-SignUp\n2-SignIn");
        //Validate input
        int choice = scanner.nextInt();
        switch (choice){
            case 1:{
                System.out.println("1-SignUp using Bank Account.\n2-SignUp using E-wallet Account.");
                int signupChoice = scanner.nextInt();
                //Validate input
                switch (signupChoice){
                    case 1: break; //userSignOptions.setSignUp(new BankSignUp)
                    case 2: break; //userSignOptions.setSignUp(new EwalletSignUp)
                }
                signUp();
                break;
            }
            case 2:{
                signIn();
                break;
            }
        }
    }

    public static void displayUser(){
        System.out.println("Welcome ,"+currentUser.getUserName()+"!");
        System.out.println("Current Balance: "+ currentUser.getInstaPayAccount().getAccount().getBalance());
    }

    private void signIn(){
        //userSignOptions.signIn(userName,Password); database can not see usersignoptions?
        //currentUser = userSignOptions.getUser();
    }
    private void signUp(){
        //userSignOptions.signUp();
        //currentUser = userSignOptions.getUser();
    }
    public static void main(String[] args) {
        System.out.println("Hello world!!");
    }
}
