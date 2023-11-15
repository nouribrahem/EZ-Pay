package UserManagement;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.EwalletAccount;
import Accounts.Providers.*;
import DataBase.*;
import Authentication.*;
import Transaction.*;

import java.security.Provider;
import java.util.Scanner;

public class UserAction {
    private User currentUser;
    private UserDatabase userDatabase;
    private TransactionDatabase transactionDatabase;
    private Authentication authentication;
    private Transaction transaction;
    {
        transaction = new Transaction();
        authentication = new Authentication();
        userDatabase = new UserDatabase();
        transactionDatabase = new TransactionDatabase();
    }
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
    public int displayUserMenu() {
        int choice;
        do {
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

            if (choice <= 0 || choice >= 7) {
                System.out.println("Please enter a valid input 1 through 6!");
            }

        } while (choice <= 0 || choice >= 7);

        return choice;
    }

    public int displaySignUpMenu(){
        int signupChoice;
        do {
            System.out.println("1-SignUp using Bank Account.\n2-SignUp using E-wallet Account.");
            signupChoice = scanner.nextInt();
            if(signupChoice != 1 && signupChoice != 2){
                System.out.println("Please enter a valid input 1 or 2!");
            }
        } while (signupChoice != 1 && signupChoice != 2);
        return signupChoice;
    }

    public void displayUser(){
        System.out.println("Welcome, "+currentUser.getUserName()+"!");
        System.out.println("Current Balance: "+ currentUser.getInstaPayAccount().getAccount().getBalance());
    }
    public int displayBankProvidersOptions(){
        int choice;
        do {
            System.out.println("Please enter receiving bank provider");
            for (BankProviders bank: BankProviders.values()){
                System.out.println(bank.ordinal()+1+ "- " + bank);
            }
            choice = scanner.nextInt();
            if (choice <= 0 || choice >= 4) {
                System.out.println("Please enter a valid input 1 through 3!");
            }

        } while (choice <= 0 || choice >= 4);
        return choice;
    }
    private int displayEWalletProvidersOptions() {
        int choice;
        do {
            System.out.println("Please enter receiving ewallet provider");
            for (EwalletProviders ewalletProvider: EwalletProviders.values()){
                System.out.println(ewalletProvider.ordinal()+1+ "- " + ewalletProvider);
            }
            choice = scanner.nextInt();
            if (choice <= 0 || choice >= 4) {
                System.out.println("Please enter a valid input 1 through 3!");
            }

        } while (choice <= 0 || choice >= 4);
        return choice;
    }
    public BankAccount getRecievingBankAccount(AccountProvider accountProvider){
        BankAccount bankAccount= new BankAccount(accountProvider);
        String bankNum;
        int i = 0;
        do{
            if(i != 0){
                System.out.println("Please enter a valid receiving account number");
            }
            System.out.println("Please enter receiving account number");
            bankNum = scanner.nextLine();
            bankAccount.setBankNumber(bankNum);
            i++;
        }while(!bankAccount.getProvider().verifyAccount());
        return bankAccount;
    }
    private EwalletAccount getRecievingEwalletAccount(EwalletAccountProvider accountProvider) {
        EwalletAccount ewalletAccount = new EwalletAccount(accountProvider);
        String mobileNum;
        int i = 0;
        do{
            if(i != 0){
                System.out.println("Please enter a valid receiving mobile number");
            }
            System.out.println("Please enter receiving mobile number");
            mobileNum = scanner.nextLine();
            ewalletAccount.setMobileNumber(mobileNum);
            i++;
        }while(!ewalletAccount.getProvider().verifyAccount());
        return ewalletAccount;
    }
    private String inputUsername() {
        System.out.println("Please enter your username");
        return scanner.nextLine();
    }

    private String inputPassword() {
        System.out.println("Please enter your password");
        return scanner.nextLine();
    }
    private double inputAmount() {
        System.out.println("Please enter amount of money to transfer");
        return scanner.nextDouble();
    }
    private String inputInstaPayUsername() {
        String username;
        int i = 0;
        do{
            if(i != 0){
                System.out.println("Please enter a valid receiving username");
            }
            System.out.println("Please enter receiving account's username");
            username = scanner.nextLine();
            i++;
        }while(!userDatabase.search(username));
        return username;
    }

    private void signIn(){
        String username,password;
        username = inputUsername();
        password = inputPassword();
        if (authentication.signIn(username,password)){
            currentUser = authentication.getSigninUser();
        }else{
            System.out.println("Failed to sign in!");
        }
    }
    private void signUp(){
        if(authentication.signUp()){
            currentUser = authentication.getSigninUser();
        }else{
            System.out.println("Failed to sign up!");
        }
    }
    void transferToBankAccount(){
        BankAccountProvider accountProvider= new BankAccountProvider();
        double amount = inputAmount();
        switch (displayBankProvidersOptions()){
            case 1: {
                accountProvider = new CIBBank();
                break;
            }
            case 2:{
                accountProvider = new NBEBank();
                break;
            }
            case 3:{
                accountProvider = new QNBBank();
                break;
            }
        }
        BankAccount receivingAccount = getRecievingBankAccount(accountProvider);
        Account sendingAccount = currentUser.getInstaPayAccount().getAccount();
        Transaction sendingUserTransaction;
        if(sendingAccount instanceof BankAccount && sendingAccount.getBalance() >= amount){
            sendingUserTransaction = transaction.makeTransaction(receivingAccount,sendingAccount,amount);
            transactionDatabase.addTransaction(sendingAccount,sendingUserTransaction);
            if(userDatabase.isRegisteredAccount(receivingAccount)){
                Transaction receivingUserTransaction = sendingUserTransaction;
                receivingUserTransaction.setType(TransactionType.recieve);
                transactionDatabase.addTransaction(receivingAccount,receivingUserTransaction);
            }
            System.out.println("Transferring to bank account succeeded!");

        }else{
            System.out.println("Transferring to bank account failed!");
        }
    }
    void transferToEWalletAccount(){
        EwalletAccountProvider accountProvider = null;
        double amount = inputAmount();
        switch (displayEWalletProvidersOptions()){
            case 1: {
                accountProvider = new VodafoneWallet();
                break;
            }
            case 2:{
                accountProvider = new FawryEwallet();
                break;
            }
            case 3:{
                accountProvider = new CIBEwallet();
                break;
            }
        }
        EwalletAccount receivingAccount = getRecievingEwalletAccount(accountProvider);
        Account sendingAccount = currentUser.getInstaPayAccount().getAccount();
        Transaction sendingUserTransaction;
        if(sendingAccount.getBalance() >= amount){
            sendingUserTransaction = transaction.makeTransaction(receivingAccount,sendingAccount,amount);
            transactionDatabase.addTransaction(sendingAccount,sendingUserTransaction);
            if(userDatabase.isRegisteredAccount(receivingAccount)){
                Transaction receivingUserTransaction = sendingUserTransaction;
                receivingUserTransaction.setType(TransactionType.recieve);
                transactionDatabase.addTransaction(receivingAccount,receivingUserTransaction);
            }
            System.out.println("Transferring to ewallet account succeeded!");

        }else{
            System.out.println("Transferring to ewallet account failed!");
        }
    }
    void transferToInstapayAccount(){
        String username = inputInstaPayUsername();
        double amount = inputAmount();
        Account receivingAccount = userDatabase.getUser(username).getInstaPayAccount().getAccount();
        Account sendingAccount = currentUser.getInstaPayAccount().getAccount();
        Transaction sendingUserTransaction;
        if(sendingAccount instanceof BankAccount || receivingAccount instanceof EwalletAccount){
            if(sendingAccount.getBalance() >= amount){
                sendingUserTransaction = transaction.makeTransaction(receivingAccount,sendingAccount,amount);
                transactionDatabase.addTransaction(sendingAccount,sendingUserTransaction);
                if(userDatabase.isRegisteredAccount(receivingAccount)){
                    Transaction receivingUserTransaction = sendingUserTransaction;
                    receivingUserTransaction.setType(TransactionType.recieve);
                    transactionDatabase.addTransaction(receivingAccount,receivingUserTransaction);
                }
                System.out.println("Transferring to instapay account succeeded!");

            }else{
                System.out.println("Transferring to instapay account failed!");
            }
        }else{
            System.out.println("Transferring to instapay account failed!");
        }
    }




    public static void main(String[] args) {
        UserAction userAction = new UserAction();
//        userAction.transferToBankAccount();
    }
}
