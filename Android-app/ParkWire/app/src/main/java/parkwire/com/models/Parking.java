package parkwire.com.models;

public class Parking {
    private float lat;
    private float lon;
    private Boolean isFree;
    private Boolean isMeter;

    public Parking(float lat, float lon, Boolean free, Boolean meter){
        this.lat = lat;
        this.lon = lon;
        this.isFree = free;
        this.isMeter = meter;
    }
}
