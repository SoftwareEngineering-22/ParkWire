package parkwire.com.models;


public class Paid extends Parking{
    private int capacity;
    private float cost;
    public Paid(float lat, float lon, Boolean free, Boolean meter, int cap, float cost){
        super(lat, lon, free, meter);
        this.capacity = cap;
        this.cost = cost;
    }

    public void setCost(){
        this.cost = cost;
    }

    public float getCost(){
        return cost;
    }
}
