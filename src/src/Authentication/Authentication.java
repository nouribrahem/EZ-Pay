package Authentication;
import Authentication.SignUp.SignUp;
import Authentication.SignUp.BankSignUp;
import Authentication.SignUp.SignUp;

import DataBase.UserDatabase;
import UserManagement.User;

public class Authentication {
    private SignUp signUp ;
    private User user = new User();
    UserDatabase userDatabase = new UserDatabase();

    public boolean signIn(String username, String password){
        userDatabase.fillData();


        user.setUserName(username);
        user.setPassword(password);


        if(userDatabase.searchSignin(username, password)){
            System.out.println("Login successful");
            user = userDatabase.getUser(username);

            return true;
        }else{
            System.out.println("User name or password incorrect");
            return false;
        }

    }
    public boolean signUp(){
        if(signUp.signUp()){
            user = signUp.getSignupUser();
            return true;
        }
        else {return false;}
    }

    public User getSigninUser(){
        return user;
    }

    public void setSignUp(SignUp signUp) {
        this.signUp = signUp;
    }
}
