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

    public void reward(){
        this.points =+ 5;
    }

    public Request writeMessage(float parkLat, float parkLon){
        System.out.println("Write your message.");
        Scanner msgScanner = new Scanner(System.in);
        String mess = msgScanner.nextLine();
        return new Request(mess, parkLat, parkLon);
    }

    public void sendAccept(){

    }

    public void sendDeny(){

    }

    public void returnMap(){

    }

}
