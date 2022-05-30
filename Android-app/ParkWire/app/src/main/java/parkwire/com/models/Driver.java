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

    public Request writeMessage(float parkLat, float parkLon){
        System.out.println("Write your message.");
        Scanner msgScanner = new Scanner(System.in);
        String mess = msgScanner.nextLine();
        Request req = new Request(mess, parkLat, parkLon);
        return req;
    }

    public void reward(Request req){
        if(this.getUsername() == req.getSender().getUsername()) {
            this.points =+2;
        }
        else if(this.getUsername() == req.getRecipient().getUsername()){
            this.points =+5;
        }
        //if the sender has parked in the requested spot and submitted an estimate, they get 3 more points.
    }

    public void sendAccept(){

    }

    public void sendDeny(){

    }

    public void returnMap(){

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

    public void viewHistory(){
        String history_q = "select * from history as h" +
                "inner join drivers as dr on dr.user_id = h.user_id" +
                "inner join user as u on u.user_id = dr.user_id" +
                "where u.username = ?";

        Connection con = new Database().connect();
        try{
            PreparedStatement ps = con.prepareStatement(history_q);
            ps.setString(1, super.getUsername());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("Date: " + rs.getTimestamp("parked") + "Left: " + rs.getTimestamp("left_parking") + "Paid: " + rs.getFloat("payment ") + "\n");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
