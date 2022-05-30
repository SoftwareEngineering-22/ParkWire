package parkwire.com.models;

public class Filters {
    boolean free;
    boolean meter;
    int range;

    public Filters(boolean f, boolean m, int rng){
        this.free = f;
        this.meter = m;
        this.range = rng;
    }

}
