package UserManagement;

import DataBase.UserDatabase;

import java.util.Scanner;

public class UserAction {
    private User currentUser;
    private UserDatabase userDatabase;
//    private Authentication authentication;
    Scanner scanner = new Scanner(System.in);


    public int displaySignMenu(){
        System.out.println("Welcome to EZ-Pay!\n1-SignUp\n2-SignIn");
        int choice = scanner.nextInt();
        while(choice != 1 && choice != 2){
            System.out.println("Please enter a valid input 1 or 2!");
            System.out.println("1-SignUp\n2-SignIn");
            choice = scanner.nextInt();
        }
        return choice;
    }
    public int displayUserMenu(){
        System.out.println("""
                Please choose operation number:
                1. Transfer to bank account.
                2. Transfer to eWallet account.
                3. Transfer to instapay account.
                4. Pay gas bill.
                5. Pay electricity bill.
                6. Pay water Bill.
                """);
        int choice = scanner.nextInt();
        while(choice <= 0 || choice >= 7){
            System.out.println("Please enter a valid input 1 through 6!");
            System.out.println("""
                Please choose operation number:
                1. Transfer to bank account.
                2. Transfer to eWallet account.
                3. Transfer to instapay account.
                4. Pay gas bill.
                5. Pay electricity bill.
                6. Pay water Bill.
                """);
            choice = scanner.nextInt();
        }
        return choice;
    }
    public int displaySignUpMenu(){
        System.out.println("1-SignUp using Bank Account.\n2-SignUp using E-wallet Account.");
        int signupChoice = scanner.nextInt();
        while(signupChoice != 1 && signupChoice != 2){
            System.out.println("Please enter a valid input 1 or 2!");
            System.out.println("1-SignUp using Bank Account.\n2-SignUp using E-wallet Account.");
            signupChoice = scanner.nextInt();
        }
        return signupChoice;
    }

    public void displayUser(){
        System.out.println("Welcome, "+currentUser.getUserName()+"!");
        System.out.println("Current Balance: "+ currentUser.getInstaPayAccount().getAccount().getBalance());
    }

//    private void signIn(){
//        if (authentication.signIn()){
//            currentUser = authentication.getUser();
//        }else{
//            System.out.println("Failed to sign in!");
//        }
//    }
//    private void signUp(){
//        if(authentication.signUp()){
//            currentUser = authentication.getUser();
//        }else{
//            System.out.println("Failed to sign up!");
//        }
//    }
    public static void main(String[] args) {
        UserAction userAction = new UserAction();
    }
}
