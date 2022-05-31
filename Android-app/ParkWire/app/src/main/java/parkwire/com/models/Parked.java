package parkwire.com.models;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Parked extends Driver {
    private float parkingLocLatitude;
    private float parkingLocLongitude;
    private Timestamp timestamp;
    private Timestamp timeEstimate;

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // για print timestamp System.out.println(dateFormat.format(timestamp));

    public Parked(String email, String username, String pass, float lat, float lon, int pts, Timestamp timeEstimate) {
        super(email, username, pass, lat, lon, pts);
        this.parkingLocLatitude = lat;
        this.parkingLocLongitude = lon;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.timeEstimate = timeEstimate;
        Database db = new Database();
        Connection con = db.connect();
        try {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO parked_driver VALUES (?, ?, ?, ?, ?)");
            pstm.setString(1, username);
            pstm.setFloat(2, this.parkingLocLatitude);
            pstm.setFloat(3, this.parkingLocLongitude);
            pstm.setTimestamp(4, this.timestamp);
            pstm.setTimestamp(5, this.timeEstimate);
            if (pstm.executeUpdate() != 0) {
                System.out.println("Parked Driver inserted");
            } else
                System.out.println("Something went wrong");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float getParkingLocLatitude() {
        return this.parkingLocLatitude;
    }

    public float getParkingLocLongitude() {
        return this.parkingLocLongitude;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public Timestamp getTimeEstimate() {
        return timeEstimate;
    }

    public void setParkedLocation(float parkingLocLatitude, float parkingLocLongitude) {
        this.parkingLocLatitude = parkingLocLatitude;
        this.parkingLocLongitude = parkingLocLongitude;
    }

    public void setTimeEstimate(Timestamp timeEstimate) {
        this.timeEstimate = timeEstimate;
        Database db = new Database();
        Connection con = db.connect();
        try {
            PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET timeestimate = ? WHERE username = ?");
            pstm.setTimestamp(1, timeEstimate);
            pstm.setString(2, this.getUsername());
            if (pstm.executeUpdate() != 0) {
                System.out.println("Time Estimate Updated");
            } else
                System.out.println("Something went wrong");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
//                new Timestamp(System.currentTimeMillis())
    }

    public long calcRemEstimate() {
        Timestamp timestamp = getTimestamp();
        Timestamp timeEstimate = getTimeEstimate();
        long diff = timeEstimate.getTime() - timestamp.getTime();
        return TimeUnit.MILLISECONDS.toSeconds(diff);
    }

}
