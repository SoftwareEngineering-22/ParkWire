package parkwire.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Valet extends User{
    private String businessName;
    private String info;

    public Valet(String email, String username, String pass, String name, String info){
        super(email, username, pass);
        this.businessName = name;
        this.info = info;
    }

    public int showAvailableSeats(){
        Connection con = new Database().connect();

        String q = "SELECT capacity FROM paid_parking as pp" +
                    "JOIN users as u ON u.user_id = pp.user_id" +
                    "WHERE u.username = ?";

        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, super.getUsername());
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return rs.getInt("capacity");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void saveAvailableSeats(int cap){
        Connection con = new Database().connect();
        try{
            String q = "UPDATE capacity SET capacity = ? FROM paid_parking as pp" +
                        "INNER JOIN valet as v ON v.username = pp.username" +
                        "WHERE pp.username = ?";
            PreparedStatement ps = con.prepareStatement(q);

            ps.setInt(1, cap);
            ps.setString(2, super.getUsername());
            ps.executeUpdate();
            System.out.println("Update completed successfully");
            ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public int updateRequest(){
        return 1;
    }
}
