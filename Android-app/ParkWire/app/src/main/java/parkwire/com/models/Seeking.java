package parkwire.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Seeking extends Driver{

    public Seeking(String email, String username, String pass, float lat, float lon, int pts){
        super(email, username, pass, lat, lon, pts);
    }

    public void parkedIt(){
        int timeEstimate = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Scanner estScanner = new Scanner(System.in);
        String estStr = estScanner.next();
        try {
            timeEstimate = Integer.parseInt(estStr);
        }
        catch(NumberFormatException ex) {
            ex.printStackTrace();
        }

        //connection
        Connection con = new Database().connect();
        try{
            //query
            String insertParked = "insert into parked_driver" +
                    "values(?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertParked);
            ps.setString(1, super.getUsername());
            ps.setFloat(2, super.getLatitude());
            ps.setFloat(3, super.getLongitude());
            ps.setTimestamp(4, timestamp);
            ps.setInt(5, timeEstimate);
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void locationCheck(Request req) {
        if ((getLatitude() == req.getReqLat()) && (getLongitude() == req.getReqLon())) {
            System.out.println("You've reached your destination!");
        }
    }

    public int setPaidTime(){
        int paidEstimate = 0;
        System.out.println("Insert your estimate.");
        Scanner est = new Scanner(System.in);
        String estStr = est.next();
        try {
            paidEstimate = Integer.parseInt(estStr);
        }
        catch(NumberFormatException ex) {
            ex.printStackTrace();
        }
        return paidEstimate;
    }
}
