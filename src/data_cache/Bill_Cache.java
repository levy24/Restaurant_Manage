package data_cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connectDTB.connect;

public class Bill_Cache {
	public boolean addBill(int currentBillID, String time, long total, int tableID, String empID) {
        try {
        	connect connector = new connect();
	        Connection con = connector.connection;
            String sql = "INSERT INTO bill (bill_id, Time, Total, table_id, Emp_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, currentBillID);
            pstmt.setString(2, time);
            pstmt.setLong(3, total);
            pstmt.setInt(4, tableID);
            pstmt.setString(5, empID);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean addOrderDetail(int billID, int itemID, int quantity) {
        try {
        	connect connector = new connect();
	        Connection con = connector.connection;
            String sql = "INSERT INTO order_details (bill_ID, item_id, Quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, billID);
            pstmt.setInt(2, itemID);
            pstmt.setInt(3, quantity);
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