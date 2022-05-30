package parkwire.com.models;

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

}
