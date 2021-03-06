package parkwire.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
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

    public boolean validateUsername(final String username) {
        String regex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public boolean validatePassword(final String pswd) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pswd);
        return matcher.matches();
    }

    public boolean validateEmail(final String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // this method simulates ui form
    public String showUsernameForm(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Current Username: " + this.username);
        System.out.println("Enter new  username:");

        String username = myObj.nextLine();
        System.out.println("Username is: " + username);

        return username;
    }

    // this method simulates ui form
    public String showPasswordForm(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Current Password: " + this.password);
        System.out.println("Enter new password:");

        String password = myObj.nextLine();
        System.out.println("Password is: " + password);

        return password;
    }

    public void uploadUsername(String newUsername){
        Connection con = new Database().connect();
        // check if already  exists
        try {
            boolean exists = true;
            PreparedStatement pstm = con.prepareStatement("SELECT username from users where username = ?");
            pstm.setString(1, newUsername);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                if(!rs.getString("username").equals(newUsername)) exists = false;
            }

            if (!exists){
                pstm = con.prepareStatement("update users set username = ? where email = ?");
                pstm.setString(1, newUsername);
                pstm.setString(2, this.getEmail());
                if(pstm.executeUpdate() != 0 ){
                    System.out.println("Username updated successfully");
                    this.username = newUsername;
                }else
                    System.out.println("Something went wrong");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadPassword(String newPassword){
        Connection con = new Database().connect();
        try {
            PreparedStatement pstm = con.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            pstm.setString(1, newPassword);
            pstm.setString(2, this.getUsername());
            if (pstm.executeUpdate() != 0){
                System.out.println("Password Updated sucessfully");
                this.password = newPassword;
            }
            else
                System.out.println("Something went wrong");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showError(){System.out.println("Invalid input!");}

    public void changeUsername(){
        String newUsername = this.showUsernameForm();
        if (this.validateUsername(newUsername)) {
            this.uploadUsername(newUsername);
        }else {
            this.showError();
        }
    }

    public void changePassword(){
        String newPass = this.showPasswordForm();
        if (this.validatePassword(newPass)) {
            this.uploadPassword(newPass);
        }else
            this.showError();
    }

    public void viewHistory(){
        // check Polymorphsim on viewHistory
    }

}
