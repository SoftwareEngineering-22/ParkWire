package parkwire.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Seeking extends Driver{

    public Seeking(String email, String username, String pass, double lat, double lon, int pts){
        super(email, username, pass, lat, lon, pts);
    }

    public ArrayList<Paid> searchPaid(){
        Connection con = new Database().connect();
        String query = "SELECT * FROM paid_parking as pp INNER JOIN valet as v ON v.username = pp.username";
        ArrayList<Paid> arr = new ArrayList<Paid>();

        try {
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
                arr.add(new Paid(rs.getDouble("parkingloclatitude"), rs.getDouble("parkingloclongitude"), false,
                        false, rs.getInt("capacity"), rs.getFloat("cost"), rs.getString("info")));
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

       public void parkedIt(String estStr){
        Timestamp timeEstimate;
        //Scanner estScanner = new Scanner(System.in);
       // String estStr = estScanner.next();
        try {
            timeEstimate = Timestamp.valueOf(estStr);
            new Parked(getEmail(), getUsername(), getPassword(), getLatitude(), getLongitude(), getPoints(), timeEstimate);
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
        int time = 2;
        System.out.println("How long are you willing to stay? (in hours)");
        return time;
    }
}
