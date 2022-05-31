package parkwire.com.models;


public class Paid extends Parking{
    private int capacity;
    private float cost;
    private String info;
    public Paid(double lat, double lon, Boolean free, Boolean meter, int cap, float cost, String info){
        super(lat, lon, free, meter);
        this.capacity = cap;
        this.cost = cost;
        this.info = info;
    }

    public String introduce(){
        return "Capacity: " + this.capacity + "Cost: " + this.cost + "Info: " + this.info;
    }

    public float calculateCost(int time){return this.cost * time;}

    public void setCost(){
        this.cost = cost;
    }

    public float getCost(){
        return cost;
    }
}
