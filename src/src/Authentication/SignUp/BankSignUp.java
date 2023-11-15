package Authentication.SignUp;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.InstaPayAccount;
import Accounts.Providers.*;
import UserManagement.User;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public abstract class BankSignUp extends SignUp{
    public BankSignUp(OTPManager otp) {
        super(otp);
    }

//    public User getUgetSignupUser() {}
    public boolean createAccount() {
        //TODO(add input to create type of provider)
        Scanner in = new Scanner(System.in);
        BankAccountProvider bankProvider = null;
        System.out.println("Please choose the bank provider:" +
                "1-CIB" +
                "2-NBE" +
                "QNB");
        String type = in.next();

        if (Objects.equals(type, "CIB")) {
            bankProvider = new CIBBank();
        }
        else if(Objects.equals(type, "NBE")){
            bankProvider = new NBEBank();
        }
        else if(Objects.equals(type, "QNB")){
            bankProvider = new QNBBank();
        }
        else{
            System.out.println("Invalid provider type.");
        }

        BankAccount account = new BankAccount(bankProvider);

        System.out.println("Please enter your mobile number.");
        String number = in.next();
        if (!number.matches("\\d+")) {
            System.out.println("The mobile number must contain only digits [0-9].");
        } else account.setMobileNumber(number);

        System.out.println("Please enter your bank number.");
        String bankNumber = in.next();
        if (!bankNumber.matches("\\d+")) {
            System.out.println("The bank number must contain only digit[0-9].");
        } else account.setBankNumber(bankNumber);


        boolean verify = account.getProvider().verifyAccount();
        if (verify) {
            otp.sendOTP(number);
            System.out.println("Please enter the otp number.");
            String OTPNumber = in.next();
            if (otp.verifyOTP(OTPNumber)) {
                this.instapayAccount.setAccount( account);

                return true;
            } else {
                System.out.println("The OTP number you have entered is not correct.");
            }

        } else {
            System.out.println("The bank cannot be verified");
        }
        return false;
    }

    public InstaPayAccount getAccount(){
        return instapayAccount;
    }

    public static void main(String[] args){
//        BankAccount account = new BankAccount();
//        account.setBankNumber("123");
//        account.setMobileNumber("0123");

    }
}
