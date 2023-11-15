package Authentication.SignUp;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.EwalletAccount;
import Accounts.InstaPayAccount;
import Accounts.Providers.*;

import java.util.Objects;
import java.util.Scanner;

public class WalletSignUp extends SignUp{
    public WalletSignUp() {
        super(new OTPManager());
    }

    public  boolean createAccount(){
        Scanner in = new Scanner(System.in);


        System.out.println("Please enter The type of .");

        EwalletAccountProvider ewalletProvider = null;
        //TODO(add input to create type of provider)

        int choice;
        do{
            System.out.println("Please enter the bank provider");
            for(EwalletProviders wallet: EwalletProviders.values()){
                System.out.println(wallet.ordinal()+1+"- "+ wallet);
            }
            choice = in.nextInt();
            if (choice <= 0 || choice >= 4) {
                System.out.println("Please enter a valid input 1 through 3!");
            }

        } while (choice <= 0 || choice >= 4);

        if (choice==1) {
            ewalletProvider = new CIBEwallet();
        }
        else if(choice==2){
            ewalletProvider = new FawryEwallet();
        }
        else {
            ewalletProvider = new VodafoneWallet();
        }

        EwalletAccount ewalletAccount = new EwalletAccount(ewalletProvider);

        System.out.println("Please enter your mobile number.");
        String number = in.next();
        if(!number.matches("\\d+")){
            System.out.println("The mobile number must contain only digits [0-9].");
        }
        else ewalletAccount.setMobileNumber(number);

        boolean verify = instapayAccount.getAccount().getProvider().verifyAccount();
        if (verify) {
            otp.sendOTP(number);
            System.out.println("Please enter the otp number.");
            String OTPNumber = in.next();
            if (otp.verifyOTP(OTPNumber)) {
                this.instapayAccount.setAccount(ewalletAccount);
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

    }

