package UserManagement;

import Accounts.BankAccount;
import Accounts.InstaPayAccount;
import Accounts.Providers.BankProvider;
import Accounts.Providers.Provider;
import DataBase.DatabaseManager;
import PayBills.BillPayment;
import PayBills.ElectricityBillPayment;
import PayBills.GasBillPayment;
import PayBills.WaterBillPayment;

import java.util.Scanner;

public class UserAction {
    private static User currentUser;
    private DatabaseManager databaseManager;
    private BillPayment billPayment;

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

    private void displayInternalMenu() {
        Scanner scanner = new Scanner(System.in);
        //options user can do inside the app
        System.out.println("Enter the number of your choice ..\n");
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("There is a lot you can do ..\nWhat do you want to do ?\n" +
                    " 1 - Inquire about your balance .\n" +
                    " 2 - Pay bills (Gas , Electricity , Water) .\n" +
                    " 3 - Transfer to another EZ-Pay account .\n" +
                    " 4 - Transfer to a Wallet .\n");
            if (currentUser.getInstaPayAccount().getAccount() instanceof BankAccount) {
                System.out.println(" 5 - Transfer to Bank Account .\n");
            }
            int serviceChoice = scanner.nextInt();
            switch (serviceChoice) {
                case 1: {
                    validChoice = true;
                    break; //Show balance
                }
                case 2: {
                    payBills();
                    validChoice = true;
                    break;
                }
                case 3: {
                    validChoice = true;
                    System.out.println("Transferring to another EZ-Pay account");
                    break; //Transferring to another EZ-Pay account
                }
                case 4: {
                    validChoice = true;
                    System.out.println("Transferring to a Wallet");
                    break; //Transferring to a Wallet
                }
                case 5: {
                    validChoice = true;
                    System.out.println("Transferring to Bank Account");
                    break; //Transferring to Bank Account
                }
                default: {
                    System.out.println("Sorry! Invalid choice :(\n");
                }
            }
        }
    }

    private void payBills() {
//        currentUser = new User();
//        Provider bankProvider = new BankProvider();
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setBalance(1200.0);
//        bankAccount.setProvider(bankProvider);
//        InstaPayAccount instaPayAccount = new InstaPayAccount();


        Scanner input = new Scanner(System.in);
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("What is the type of the bill you want ?\n" +
                    " 1 - Gas .\n" +
                    " 2 - Electricity .\n" +
                    " 3 - Water .\n" +
                    " 4 - Back to previous menu .\n");
            int billChoice = input.nextInt();
            switch (billChoice) {
                case 1: {
                    billPayment = new GasBillPayment();
                    validChoice = true;
                    break;
                }
                case 2: {
                    billPayment = new ElectricityBillPayment();
                    validChoice = true;
                    break;
                }
                case 3: {
                    billPayment = new WaterBillPayment();
                    validChoice = true;
                    break;
                }
                case 4: {
                    return;
                }
                default: {
                    System.out.println("Sorry! Invalid choice :(\n");
                }
            }


//            billPayment.setUtilityDataType();
            billPayment.enterData();

            boolean validInternalChoice = false;
            while (!validInternalChoice) {
                System.out.println("Do you want to :\n" +
                        " 1 - Pay the bill .\n" +
                        " 2 - Show the receipt .\n" +
                        " 3 - Back to the previous menu .\n");
                int billInternalChoice = input.nextInt();
                switch (billInternalChoice) {
                    case 1: {
                        billPayment.payBill(currentUser.getInstaPayAccount().getAccount());
                        validInternalChoice = true;
                        break;
                    }
                    case 2: {
                        billPayment.showReceipt(currentUser);
                        break;
                    }
                    case 3: {
                        validInternalChoice = true;
                        break;
                    }
                    default: {
                        System.out.println("Sorry! Invalid choice :(\n");
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        UserAction userAction = new UserAction();
        userAction.payBills();
    }
}
