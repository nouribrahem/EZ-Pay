package Authentication.SignUp;

import Accounts.EwalletAccount;
import Accounts.InstaPayAccount;
import Accounts.Providers.*;
import Authentication.SignUp.OTPManager;

import java.util.Scanner;

public class WalletSignUp extends SignUp {
    public WalletSignUp(OTPManager otp) {
        super(otp);
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
        while (!number.matches("0(10|11|12)\\d{8}")) {
            System.out.println("The mobile number must be 11 digit and start with 010 | 011 | 012.");
            number = in.next();

        }
        ewalletAccount.setMobileNumber(number);

        boolean verify = ewalletAccount.getProvider().verifyAccount(ewalletAccount);
        if (verify) {
            otp.sendOTP(number);
            System.out.println("Please enter the otp number.");
            String OTPNumber = in.next();
            while (!otp.verifyOTP(OTPNumber)) {
                System.out.println("The OTP number you have entered is not correct, try again.");
                OTPNumber = in.next();
            }
            ewalletAccount.setBalance(ewalletAccount.getProvider().getAccountBalance(ewalletAccount));

            this.instapayAccount = new InstaPayAccount(ewalletAccount);
            System.out.println("The wallet created successfully");
            return true;
        } else {
            System.out.println("The bank cannot be verified");
        }
        return false;
    }

    public InstaPayAccount getAccount(){
        return instapayAccount;
    }

    }

