package DataBase;

import UserManagement.User;

import java.util.List;

public class DatabaseManager {
    private static List<User> users;

    public static boolean search(String userName){
        return users.equals(userName);
    }

    public static boolean searchSignin(String name, String pass) {
        if (users.equals(name)) {
            if (users.equals(pass)) {
                return true;
            } else {
                System.out.println("Invalid password");
                return false;
            }
        } else {
            System.out.println("Invalid user name");
        }
        return false;
    }

    public static void setUsers(List<User> users) {
        DatabaseManager.users = users;
    }
}
