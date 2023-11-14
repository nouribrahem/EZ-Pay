package Authentication.SignUp;

public class OTPManager {
    private String otp;

    public void generateOTP(){
        otp = "000";

    }
    public void sendOTP(String phoneNumber){
        System.out.println(otp);

    }
    public boolean verifyOTP(String input){
        return true;
    }
}
