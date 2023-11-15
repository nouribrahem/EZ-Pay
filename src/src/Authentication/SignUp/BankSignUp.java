package Authentication.SignUp;

import Accounts.BankAccount;
import Accounts.InstaPayAccount;
import Accounts.Providers.*;
import Authentication.*;

import java.util.Scanner;

import static java.lang.Character.isDigit;

public class BankSignUp extends SignUp {
    public BankSignUp() {
        super(new OTPManager());
    }

    //    public User getUgetSignupUser() {}
    public boolean createAccount() {
        //TODO(add input to create type of provider)
        Scanner in = new Scanner(System.in);
        BankAccountProvider bankProvider = null;
        int choice;
        do {
            System.out.println("Please enter the bank provider");
            for (BankProviders bank : BankProviders.values()) {
                System.out.println(bank.ordinal() + 1 + "- " + bank);
            }
            choice = in.nextInt();
            if (choice <= 0 || choice >= 4) {
                System.out.println("Please enter a valid input 1 through 3!");
            }

        } while (choice <= 0 || choice >= 4);

        if (choice == 1) {
            bankProvider = new CIBBank();
        } else if (choice == 2) {
            bankProvider = new NBEBank();
        } else {
            bankProvider = new QNBBank();
        }

        BankAccount account = new BankAccount(bankProvider);

        System.out.println("Please enter your mobile number.");
        String number = in.next();
        while (!number.matches("0(10|11|12)\\d{8}")) {
            System.out.println("The mobile number must be 11 digit and start with 010 | 011 | 012.");
            number = in.next();

        }

        account.setMobileNumber(number);

        System.out.println("Please enter your bank number.");
        String bankNumber = in.next();
        while (!bankNumber.matches("\\d{8}")) {
            System.out.println("The bank number must be 8 digit, try again.");
            bankNumber = in.next();
        }
        account.setBankNumber(bankNumber);


        boolean verify = account.getProvider().verifyAccount(account);
        System.out.println(verify);
        if (verify) {
            otp.sendOTP(number);
            System.out.println("Please enter the otp number.");
            String OTPNumber = in.next();
            while (!otp.verifyOTP(OTPNumber)) {
                System.out.println("The OTP number you have entered is not correct, try again.");
                OTPNumber = in.next();
            }
            this.instapayAccount.setAccount(account);

            System.out.println("The bank created successfully");
            return true;
        } else {
            System.out.println("The bank cannot be verified");
        }
        return false;
    }

    public InstaPayAccount getAccount() {
        return instapayAccount;
    }
}

