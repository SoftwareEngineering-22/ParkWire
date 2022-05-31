package parkwire.com.models;

public class Request {
    private String message;
    private double reqLat;
    private double reqLon;
    private Driver sender;
    private Driver recipient;


    public Request(String msg, double rlat, double rlon){
        this.message = msg;
        this.reqLat = rlat;
        this.reqLon = rlon;
    }

    public Driver getSender() {
        return sender;
    }

    public Driver getRecipient() {
        return recipient;
    }

    public double getReqLat() {
        return reqLat;
    }

    public double getReqLon() {
        return reqLon;
    }

    public void sentMessage(){}

    public void sentNotification(){}

    public void pushNotification(){}

}

