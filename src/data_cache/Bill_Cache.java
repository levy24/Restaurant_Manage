package data_cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connectDTB.connect;

public class Bill_Cache {
	public boolean addBill(int currentBillID, String time,int tableID, int empID, long total,  int status) {
        try {
        	connect connector = new connect();
	        Connection con = connector.connection;
            String sql = "INSERT INTO bill (bill_ID, Time, Table_ID, Emp_ID, Total,  Status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, currentBillID);
            pstmt.setString(2, time);
            pstmt.setInt(3, tableID);
            pstmt.setInt(4, empID);
            pstmt.setLong(5, total);
            pstmt.setInt(6, status);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean addOrderDetail(int billID, int itemID, int quantity, int price) {
        try {
        	connect connector = new connect();
	        Connection con = connector.connection;
            String sql = "INSERT INTO order_details (bill_ID, item_id, Quantity, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, billID);
            pstmt.setInt(2, itemID);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, price);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
