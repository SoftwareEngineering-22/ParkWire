package parkwire.com.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User{
    private String email;
    private String username;
    private String password;

    public User(String ml, String usr, String pass){
        this.email = ml;
        this.username = usr;
        this.password = pass;
    }

    public String getUsername(){
        return this.username;
    }
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}

    public static boolean validateUsername(final String username) {
        String regex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean validatePassword(final String pswd) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pswd);
        return matcher.matches();
    }

    public static boolean validateEmail(final String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public int changeUsername(String newUsername){
        if (validateEmail(newUsername)) {
            this.username = newUsername;
            return 1;
        }else {
            System.out.println("Invalid Username");
            return -1;
        }
    }

    public void changePassword(String newPass){
        if (validateEmail(newPass)) {
            this.password = newPass;
        }else{
            System.out.println("Invalid password");
        }
    }

    public void viewHistory(){
        System.out.println("Depending on the user, polymorphism is applied.");
    }

    public void uploadDB(){
        System.out.println("Nothing to upload yet!");
    }

}
