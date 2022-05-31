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

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp timeEstimate;
        //Scanner estScanner = new Scanner(System.in);
        String estStr = "2022-05-31 17:19:00.000";
       // String estStr = estScanner.next();
        try {
            timeEstimate = Timestamp.valueOf(estStr);
            new Parked(getEmail(), getUsername(), getPassword(), getLatitude(), getLongitude(), getPoints(), timeEstimate);
            //kai diagrafetai to antikeimeno seeking
        }
        catch(NumberFormatException ex) {
            ex.printStackTrace();
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
