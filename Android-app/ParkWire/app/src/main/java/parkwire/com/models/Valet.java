package parkwire.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Valet extends User {
    private String businessName;
    private String info;

    public Valet(String email, String username, String pass, String name, String info) {
        super(email, username, pass);
        this.businessName = name;
        this.info = info;
    }

    public int showAvailableSeats() {
        Connection con = new Database().connect();

        String q = "SELECT capacity FROM paid_parking as pp" +
                "JOIN users as u ON u.user_id = pp.user_id" +
                "WHERE u.username = ?";

        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, super.getUsername());
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                return rs.getInt("capacity");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void showAvailableSeatsForm(Paid pp){
        Console console = System.console();
        System.out.println("Update current capacity");
        Scanner sc = new Scanner(console.reader());
        int c = sc.nextInt();
        pp.setCapacity(c);

        Connection con = new Database().connect();

        String q = "UPDATE paid_parking as pp" +
                "SET capacity = ?" +
                "JOIN users as u ON u.user_id = pp.user_id" +
                "WHERE u.username = ?";
        PreparedStatement pst = null;
        try{
            pst = con.prepareStatement(q);
            pst.setInt(1, c);
            pst.setString(2, super.getUsername());
            ResultSet rs = pst.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveAvailableSeats(int cap) {
        Connection con = new Database().connect();
        try {
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

    public int updateRequest() {
        return 1;
    }

    public void searchValetHistory(String query){
        Connection con = new Database().connect();
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, super.getUsername());
            ResultSet rs_paid = pstm.executeQuery();


            while (rs_paid.next()) {
                System.out.println("Date: " + rs_paid.getTimestamp("parked") + "Left: " + rs_paid.getTimestamp("left_parking") + "Paid: " + rs_paid.getFloat("payment ") + "\n");
            }
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewHistory() {
        String history_q = "select * from paid_parking as pp" +
                "inner join valet as v on v.username = pp.username" +
                "inner join driver_history as dh on dh.parking_id = pp.id" +
                "where h.username = ?";

        this.searchValetHistory(history_q);

    }

    public void addNewBusiness(double lat, double lon, int capacity, int cost, String contact_info, String work_hours, String address) {
        Database db = new Database();
        Connection con = db.connect();
        try {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO paid_parking VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?");
            pstm.setString(1, getUsername());
            pstm.setDouble(2, lat);
            pstm.setDouble(3, lon);
            pstm.setInt(4, capacity);
            pstm.setInt(5, cost);
            pstm.setString(6, contact_info);
            pstm.setString(7, work_hours);
            pstm.setString(8, address);
            if (pstm.executeUpdate() != 0) {
                System.out.println("Paid Parking inserted");
            } else
                System.out.println("Something went wrong");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editBusiness(double lat, double lon, int capacity, int cost, String contact_info, String work_hours, String address) {
        Database db = new Database();
        Connection con = db.connect();

        if (lat != 0 || lon != 0) {
            try {
                PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET parkingloclatitude = ?, parkingloclongitude = ? WHERE username = ?");
                pstm.setDouble(1, lat);
                pstm.setDouble(2, lon);
                pstm.setString(3, this.getUsername());
                if (pstm.executeUpdate() != 0) {
                    System.out.println("Paid Parking Location updated");
                } else
                    System.out.println("Something went wrong");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (capacity != 0) {
            try {
                PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET capacity = ? WHERE username = ?");
                pstm.setInt(1, capacity);
                pstm.setString(2, this.getUsername());
                if (pstm.executeUpdate() != 0) {
                    System.out.println("Paid Parking capacity updated");
                } else
                    System.out.println("Something went wrong");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (cost != 0) {
            try {
                PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET cost = ? WHERE username = ?");
                pstm.setInt(1, cost);
                pstm.setString(2, this.getUsername());
                if (pstm.executeUpdate() != 0) {
                    System.out.println("Paid Parking cost updated");
                } else
                    System.out.println("Something went wrong");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (contact_info != null) {
            try {
                PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET contact_info = ? WHERE username = ?");
                pstm.setString(1, contact_info);
                pstm.setString(2, this.getUsername());
                if (pstm.executeUpdate() != 0) {
                    System.out.println("Paid Parking contact_info updated");
                } else
                    System.out.println("Something went wrong");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (work_hours != null) {
            try {
                PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET work_hours = ? WHERE username = ?");
                pstm.setString(1, work_hours);
                pstm.setString(2, this.getUsername());
                if (pstm.executeUpdate() != 0) {
                    System.out.println("Paid Parking work_hours updated");
                } else
                    System.out.println("Something went wrong");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (address != null) {
            try {
                PreparedStatement pstm = con.prepareStatement("UPDATE parked_driver SET address = ? WHERE username = ?");
                pstm.setString(1, address);
                pstm.setString(2, this.getUsername());
                if (pstm.executeUpdate() != 0) {
                    System.out.println("Paid Parking address updated");
                } else
                    System.out.println("Something went wrong");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
