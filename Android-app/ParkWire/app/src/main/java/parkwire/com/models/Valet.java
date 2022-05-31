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

    public void viewHistory(){
        String history_q = "select * from paid_parking as pp" +
                "inner join valet as v on v.username = pp.username" +
                "inner join driver_history as dh on dh.parking_id = pp.id"+
                "where h.username = ?";
        Connection con = new Database().connect();
        try{
            PreparedStatement pstm = con.prepareStatement(history_q);
            pstm.setString(1, super.getUsername());
            ResultSet rs_paid = pstm.executeQuery();


            while(rs_paid.next()){
                System.out.println("Date: " + rs_paid.getTimestamp("parked") + "Left: " + rs_paid.getTimestamp("left_parking") + "Paid: " + rs_paid.getFloat("payment ") + "\n");
            }
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
