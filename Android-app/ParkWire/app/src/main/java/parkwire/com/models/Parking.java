package parkwire.com.models;

public class Parking {
    private double lat;
    private double lon;
    private boolean isFree;
    private boolean isMeter;

    public Parking(double lat, double lon, boolean free, boolean meter){
        this.lat = lat;
        this.lon = lon;
        this.isFree = free;
        this.isMeter = meter;
    }

    public double getLatitude(){return this.lat;}
    public double getLongitude(){return this.lon;}
}
