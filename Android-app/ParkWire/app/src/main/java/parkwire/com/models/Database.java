package parkwire.com.models;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database{

    private Connection connection = null;
    private final String host = "ec2-34-247-172-149.eu-west-1.compute.amazonaws.com";
    private final String database = "d20nd037ds0s85";
    private final int port = 5432;
    private final String username = "qtuiehpetqjxoe";
    private final String password= "fb47d2dabf861efc95b5a1d760e0a0026d0d88dc5ae79bd87188da1cf8992840";
    private final String url = "jdbc:postgresql://%s:%d/%s?sslmode=require&rejectUnauthorized=false";


    // constructor to create total diff db con
    public Database(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(String.format(this.url, this.host, this.port, this.database), this.username, this.password);
            System.out.println("Succesfull Connection..");
        }
        catch(Exception e) {
            System.out.println("Unsuccesful connection with db.");
            System.out.println("Error: "+ e);
        }
    }
    //method which establishes connection with  DB
    public Connection connect() {
        return this.connection;
    }

    public void printQuery(String q){
        try {
            Statement s = this.connection.createStatement();
            ResultSet rs = s.executeQuery(q);
            while(rs.next()){
                while (rs.next()) {
                    for(int i = 1; i < 4; i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}//end of class dbCon