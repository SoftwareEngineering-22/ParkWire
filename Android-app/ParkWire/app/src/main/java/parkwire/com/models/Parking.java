package parkwire.com.models;

public class Parking {
    private double lat;
    private double lon;
    private Boolean isFree;
    private Boolean isMeter;

    public Parking(double lat, double lon, Boolean free, Boolean meter){
        this.lat = lat;
        this.lon = lon;
        this.isFree = free;
        this.isMeter = meter;
    }
}
