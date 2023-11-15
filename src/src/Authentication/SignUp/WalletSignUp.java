package Authentication.SignUp;

import Accounts.Account;
import Accounts.BankAccount;
import Accounts.EwalletAccount;
import Accounts.InstaPayAccount;
import Accounts.Providers.*;

import java.util.Objects;
import java.util.Scanner;

public abstract class WalletSignUp extends SignUp{
    public WalletSignUp(OTPManager otp) {
        super(otp);
    }

    public  boolean createAccount(){
        Scanner in = new Scanner(System.in);


        System.out.println("Please enter The type of .");
        String typeProvider = in.next();

        EwalletAccountProvider ewalletProvider = null;
        //TODO(add input to create type of provider)

        System.out.println("Please choose the wallet provider:" +
                "1-CIB" +
                "2-fawry" +
                "vodafone");

        if (Objects.equals(typeProvider, "CIB")) {
            ewalletProvider = new CIBEwallet();
        }
        else if(Objects.equals(typeProvider, "fawry")){
            ewalletProvider = new FawryEwallet();
        }
        else if(Objects.equals(typeProvider, "vodafone")){
            ewalletProvider = new VodafoneWallet();
        }
        else{
            System.out.println("Invalid provider type.");
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

