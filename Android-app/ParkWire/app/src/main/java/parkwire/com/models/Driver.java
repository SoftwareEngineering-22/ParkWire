package parkwire.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver extends User {
    float currLat;
    float currLon;
    int points;

    public Driver(String email, String username, String pass, float lat, float lon, int pts){
        super(email, username, pass);
        this.currLat = lat;
        this.currLon = lon;
        this.points = pts;
    }

    public float getLatitude(){
        return this.currLat;
    }

    public float getLongitude(){
        return this.currLon;
    }

    public int getPoints(){
        return this.points;
    }

    public Request writeMessage(float parkLat, float parkLon){
        System.out.println("Write your message.");
        Scanner msgScanner = new Scanner(System.in);
        String mess = msgScanner.nextLine();
        Request req = new Request(mess, parkLat, parkLon);
        return req;
    }

    public void reward(Request req){
        if(super.getUsername() == req.getSender().getUsername()) {
            this.points =+2;
        }
        else if(super.getUsername() == req.getRecipient().getUsername()){
            this.points =+5;
        }
        if((currLat == req.getReqLat()) && (currLon == req.getReqLon())){
            this.points =+3;
        }
    }

    public void sendAccept(){

    }

    public void sendDeny(){

    }

    public void returnMap(){

    }

    public void viewHistory(){
        String paid_history_q = "select * from driver_history as dh" +
                "inner join drivers as dr on dr.username = dh.username" +
                "where h.username = ?";
        String history_q = "select * from parked_driver as pd"+
                            "inner join drivers as d on d.username = pd.username"+
                            "where d.username = ?";
        Connection con = new Database().connect();
        try{
            PreparedStatement ps_paid = con.prepareStatement(paid_history_q);
            PreparedStatement ps_h = con.prepareStatement(history_q);
            ps_paid.setString(1, super.getUsername());
            ps_h.setString(1, super.getUsername());
            ResultSet rs_paid = ps_paid.executeQuery();
            ResultSet rs_history = ps_h.executeQuery();


            while(rs_paid.next()){
                System.out.println("Your paid parking:");
                System.out.println("Date: " + rs_paid.getTimestamp("parked") + "Left: " + rs_paid.getTimestamp("left_parking") + "Paid: " + rs_paid.getFloat("payment ") + "\n");
            }
            while(rs_history.next()){
                System.out.println("Your Free and Meter parking:");
                System.out.println("Date: " + rs_history.getTimestamp("parkedtimestamp")+ "Longitude: " + rs_history.getFloat("parkingloclatitude") + "Latitude:"
                        + rs_history.getFloat("parkingloclatitude"));
            }
            ps_paid.close();
            ps_h.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
