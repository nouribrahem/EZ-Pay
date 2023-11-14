package Authentication.SignUp;

import Accounts.EwalletAccount;
import Accounts.Providers.EwalletAccountProvider;
import Accounts.Providers.VodafoneWallet;

import java.util.Scanner;

public abstract class WalletSignUp extends SignUp{
    public WalletSignUp(OTPManager otp) {
        super(otp);
    }

    public  boolean createAccount(){
        Scanner in = new Scanner(System.in);


        System.out.println("Please enter The type of .");
        String typeProvider = in.next();
        EwalletAccountProvider ewalletProvider = new VodafoneWallet();
        //TODO(add input to create type of provider)

        EwalletAccount ewalletAccount = new EwalletAccount(ewalletProvider);



        System.out.println("Please enter your mobile number.");
        String number = in.next();
        if(!number.matches("\\d+")){
            System.out.println("The mobile number must contain only digits [0-9].");
        }
        else ewalletAccount.setMobileNumber(number);

        return false;

    }
}
