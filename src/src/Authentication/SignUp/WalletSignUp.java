package Authentication.SignUp;

import Accounts.EwalletAccount;
import Accounts.Providers.EwalletProvider;

import java.util.Scanner;

public abstract class WalletSignUp extends SignUp{
    public WalletSignUp(OTPManager otp) {
        super(otp);
    }

    public  boolean createAccount(){
        EwalletAccount ewalletAccount = new EwalletAccount();

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter your mobile number.");
        String number = in.next();
        if(!number.matches("\\d+")){
            System.out.println("The mobile number must contain only digits [0-9].");
        }
        else ewalletAccount.setMobileNumber(number);

        EwalletProvider ewalletProvider = new EwalletProvider();

        System.out.println("Please enter The type of .");
        String typeProvider = in.next();

    }
}
