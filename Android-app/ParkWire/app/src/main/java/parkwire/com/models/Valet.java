package parkwire.com.models;

class Valet extends User{
    private String businessName;
    private String info;

    public Valet(String email, String username, String pass, String name, String info){
        super(email, username, pass);
        this.businessName = name;
        this.info = info;
    }

    public int showAvailableSeats(){
        System.out.println("That's the part where i connect to db to get the number of available seats");
        return 0;
    }

    public void saveAvailableSeats(){
        System.out.println("That's the part send a quare to increase seats");
    }

    public int updateRequest(){
        return 1;
    }
}
