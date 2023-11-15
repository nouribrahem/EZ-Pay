package Authentication.SignUp;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.Providers.BankAccountProvider;
import Accounts.Providers.QNBBank;

import java.util.Scanner;


public class BankSignUp extends SignUp{
    public BankSignUp() {
        super(new OTPManager());
    }

    public boolean createAccount(){
        BankAccountProvider provider = new QNBBank();
        //TODO(add input to create type of provider)

        BankAccount account = new BankAccount(provider);

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter your mobile number.");
        String number = in.next();
        if(!number.matches("\\d+")){
            System.out.println("The mobile number must contain only digits [0-9].");
        }
        else account.setMobileNumber(number);

        System.out.println("Please enter your bank number.");
        String bankNumber = in.next();
        if(!bankNumber.matches("\\d+")){
            System.out.println("The bank number must contain only digit[0-9].");
        }
        else account.setBankNumber(bankNumber);


        boolean verify = account.getProvider().verifyAccount();
        if(verify){
            otp.sendOTP(number);
            System.out.println("Please enter the otp number.");
            String OTPNumber = in.next();
            if(otp.verifyOTP(OTPNumber)){
                return true;
            }
            else{
                System.out.println("The OTP number you have entered is not correct.");
            }

        }else{
            System.out.println("The bank cannot be verified");
        }
        return false;
    }

    public static void main(String[] args){
//        BankAccount account = new BankAccount();
//        account.setBankNumber("123");
//        account.setMobileNumber("0123");

    }
}
