package parkwire.com.models;


public class Paid extends Parking{
    private int capacity;
    private float cost;
    private String info;
    public Paid(float lat, float lon, Boolean free, Boolean meter, int cap, float cost, String info){
        super(lat, lon, free, meter);
        this.capacity = cap;
        this.cost = cost;
        this.info = info;
    }

    public void setCost(){
        this.cost = cost;
    }

    public float getCost(){
        return cost;
    }
}
