package UserManagement;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.EwalletAccount;
import Accounts.Providers.*;
import Authentication.SignUp.BankSignUp;
import Authentication.SignUp.OTPManager;
import Authentication.SignUp.WalletSignUp;
import Bills.BillPayment;
import Bills.*;
import DataBase.*;
import Authentication.*;
import Transaction.*;


import java.util.Scanner;

import static java.lang.System.exit;

public class UserAction {
    private User currentUser;
    private UserDatabase userDatabase;
    private TransactionDatabase transactionDatabase;
    private Authentication authentication;
    private BillPayment billPayment;
    private Transaction transaction;
    {
        transaction = new Transaction();
        authentication = new Authentication();
        userDatabase = new UserDatabase();
        authentication.setUserDatabase(userDatabase);
        transactionDatabase = new TransactionDatabase();
        billPayment = null;
    }
    void setCurrentUser(User u){
        currentUser = u;
    }
    Scanner scanner;
    public int displaySignMenu(){
        scanner = new Scanner(System.in);
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
        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("""
            \nPlease choose operation number:\n
            1. Transfer to bank account.
            2. Transfer to eWallet account.
            3. Transfer to instapay account.
            4. Pay gas bill.
            5. Pay electricity bill.
            6. Pay water Bill.
            7. Sign out.
            8. exit.
            """);

            choice = scanner.nextInt();

            if (choice <= 0 || choice > 8) {
                System.out.println("Please enter a valid input 1 through 8!");
            }

        } while (choice <= 0 || choice > 8);

        return choice;
    }

    public int displaySignUpMenu(){
        scanner = new Scanner(System.in);
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
        System.out.println("\nWelcome, "+currentUser.getUserName()+"!");
        System.out.println("Current Balance: "+ currentUser.getInstaPayAccount().getAccount().getBalance());
    }
    public int displayBankProvidersOptions(){
        scanner = new Scanner(System.in);
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
        scanner = new Scanner(System.in);
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
        scanner = new Scanner(System.in);
        BankAccount bankAccount= new BankAccount(accountProvider);
        String bankNum;
           System.out.println("Please enter receiving account number");
            bankNum = scanner.nextLine();
            bankAccount.setBankNumber(bankNum);
        while(!bankAccount.getProvider().verifyAccount(bankAccount)){
            System.out.println("Please enter a valid receiving account number");
            bankNum = scanner.nextLine();
            bankAccount.setBankNumber(bankNum);
        }
        return bankAccount;
    }
    private EwalletAccount getRecievingEwalletAccount(EwalletAccountProvider accountProvider) {
        Scanner input = new Scanner(System.in);
        EwalletAccount ewalletAccount = new EwalletAccount(accountProvider);
        String mobileNum="";
        int i = 0;
        for(EwalletAccount account:accountProvider.getEwalletRegisteredAccounts()){
            System.out.println(account.getMobileNumber());
        }
        System.out.println("Please enter receiving mobile number");
        mobileNum = input.nextLine();
        ewalletAccount.setMobileNumber(mobileNum);

        while(!ewalletAccount.getProvider().verifyAccount(ewalletAccount)){
            System.out.println("Please enter a valid receiving mobile number");
            mobileNum = input.nextLine();
            ewalletAccount.setMobileNumber(mobileNum);
            System.out.println(ewalletAccount.getMobileNumber());
        }
        return ewalletAccount;
    }
    private String inputUsername() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your username");
        return in.nextLine();
    }

    private String inputPassword() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your password");
        return in.nextLine();
    }
    private double inputAmount() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter amount of money to transfer");
        return scanner.nextDouble();
    }
    private String inputInstaPayUsername() {
        scanner = new Scanner(System.in);
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

    private boolean signIn(){
        String username,password;
        username = inputUsername();
        password = inputPassword();
        if (authentication.signIn(username,password)){
            currentUser = authentication.getSigninUser();
            return true;
        }else{
            System.out.println("Failed to sign In!");
            return false;
        }
    }
    private boolean signUp(){
        if(authentication.signUp()){
            currentUser = authentication.getSigninUser();
            userDatabase.addUser(currentUser);
            return true;
        }else{
            System.out.println("Failed to sign up!");
            return false;
        }
    }
    void transferToBankAccount(){
        BankAccountProvider accountProvider = null;
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
        if(sendingAccount instanceof BankAccount && sendingAccount.getBalance() >= amount &&
                !receivingAccount.getBankNumber().equals(((BankAccount) sendingAccount).getBankNumber())){
            sendingUserTransaction = transaction.makeTransaction(receivingAccount,sendingAccount,amount);
            transactionDatabase.addTransaction(sendingAccount,sendingUserTransaction);
            userDatabase.updateUserBalance(currentUser);
            User recievingUser = userDatabase.isRegisteredAccount(receivingAccount);
            if( recievingUser != null){
                Transaction receivingUserTransaction = sendingUserTransaction;
                receivingUserTransaction.setType(TransactionType.recieve);
                transactionDatabase.addTransaction(receivingAccount,receivingUserTransaction);
                userDatabase.updateUserBalance(recievingUser);
            }
            else{
                receivingAccount.getProvider().updateAccountBalance(receivingAccount,amount);
            }
            userDatabase.updateUserBalance(currentUser);
            currentUser.getInstaPayAccount().getAccount().setBalance(sendingAccount.getProvider().getAccountBalance(sendingAccount));
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
            userDatabase.updateUserBalance(currentUser);
            currentUser.getInstaPayAccount().getAccount().setBalance(sendingAccount.getProvider().getAccountBalance(sendingAccount));
            User recievingUser = userDatabase.isRegisteredAccount(receivingAccount);
            if(recievingUser != null){
                Transaction receivingUserTransaction = sendingUserTransaction;
                receivingUserTransaction.setType(TransactionType.recieve);
                transactionDatabase.addTransaction(receivingAccount,receivingUserTransaction);
                userDatabase.updateUserBalance(recievingUser);
            }
            System.out.println("Transferring to ewallet account succeeded!");
        }else{
            System.out.println("Transferring to ewallet account failed!");
        }
    }
    void transferToInstapayAccount(){
        String username = inputInstaPayUsername();
        double amount = inputAmount();
        User recievingUser = userDatabase.getUser(username);
        Account receivingAccount = recievingUser.getInstaPayAccount().getAccount();
        Account sendingAccount = currentUser.getInstaPayAccount().getAccount();
        Transaction sendingUserTransaction;
        if(sendingAccount instanceof BankAccount || receivingAccount instanceof EwalletAccount){
            if(sendingAccount.getBalance() >= amount){

                sendingUserTransaction = transaction.makeTransaction(receivingAccount,sendingAccount,amount);
                transactionDatabase.addTransaction(sendingAccount,sendingUserTransaction);
                Transaction receivingUserTransaction = sendingUserTransaction;
                receivingUserTransaction.setType(TransactionType.recieve);
                transactionDatabase.addTransaction(receivingAccount,receivingUserTransaction);

                userDatabase.updateUserBalance(currentUser);
                currentUser.getInstaPayAccount().getAccount().setBalance(sendingAccount.getProvider().getAccountBalance(sendingAccount));
                userDatabase.updateUserBalance(recievingUser);
                System.out.println("Transferring to instapay account succeeded!");

            }else{
                System.out.println("Transferring to instapay account failed!");
            }
        }else{
            System.out.println("Transferring to instapay account failed!");
        }
    }
    public void payGasBill(){
        billPayment = new GasBillPayment();
        billPayment.billOptions(currentUser);
    }
    public void payWaterBill(){
        billPayment = new WaterBillPayment();
        billPayment.billOptions(currentUser);
    }
    public void payElectricityBill(){
        billPayment = new ElectricityBillPayment();
        billPayment.billOptions(currentUser);
    }
    public void runSignUser(){
        while(true){
            int signingChoice = displaySignMenu();
            switch (signingChoice){
                case 1:{
                    int signUpChoice = displaySignUpMenu();
                    switch (signUpChoice){
                        case 1:{
                            authentication.setSignUp(new BankSignUp());
                            break;
                        }
                        case 2:{
                            authentication.setSignUp(new WalletSignUp(new OTPManager()));
                            break;
                        }
                    }
                    if(signUp()){
                        runApplication();
                    }
                    break;
                }
                case 2:{
                    if(signIn()){
                        runApplication();
                    }
                    break;
                }
                default: {
                    System.out.println("Thank you for using our application!");
                    exit(0);
                    break;
                }
            }
        }
    }

    public void runApplication() {

        while (true){
            displayUser();
            int choice = displayUserMenu();
            switch (choice){
                case 1:{
                    transferToBankAccount();
                    break;
                }
                case 2:{
                    transferToEWalletAccount();
                    break;
                }
                case 3:{
                    transferToInstapayAccount();
                    break;
                }
                case 4:{
                    payGasBill();
                    break;
                }
                case 5:{
                    payElectricityBill();
                    break;
                }
                case 6:{
                    payWaterBill();
                    break;
                }
                case 7:{
                    runSignUser();
                    break;
                }
                case 8:{
                    System.out.println("Thank you for using our application!");
                    exit(0);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        BankAccountProvider p1 = new BankAccountProvider();
        EwalletAccountProvider p2 = new EwalletAccountProvider();
        p1.fillAccounts();
        p2.fillAccounts();
        UserAction userAction = new UserAction();
        userAction.runSignUser();
    }
}
