package parkwire.com.models;

public class Request {
    private String message;
    private float reqLat;
    private float reqLon;

    public Request(String msg, float rlat, float rlon){
        this.message = msg;
        this.reqLat = rlat;
        this.reqLon = rlon;
    }

    public void sentMessage(){

    }

    public void sentNotification(){

    }

    public void pushNotification(){

    }
}

