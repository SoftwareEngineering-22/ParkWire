package parkwire.com.models;

public class Request {
    private String message;
    private float reqLat;
    private float reqLon;
    private Driver sender;
    private Driver recipient;


    public Request(String msg, float rlat, float rlon){
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

    public float getReqLat() {
        return reqLat;
    }

    public float getReqLon() {
        return reqLon;
    }

    public void sentMessage(){}

    public void sentNotification(){}

    public void pushNotification(){}

}

