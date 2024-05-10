package data_cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connectDTB.connect;

public class Bill_Cache {
	public boolean addBill(int currentBillID, String time,int tableID, int empID, long total,  int status) {
        try {
        	connect connector = new connect();
	        Connection con = connector.connection;
            String sql = "INSERT INTO bill (bill_ID, Time, Table_ID, Emp_Order, Emp_Cash, Total,  Status) VALUES (?, ?, ?, ?, null, ?, ?)";
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
    
    public boolean updateOrderDetailQuantity(int billID, int itemID, int quantity, int price) {        
    	try {
    	    connect connector = new connect();
    	    Connection con = connector.connection;
    	    String sql = "UPDATE order_details SET quantity = ?, total_price = ? WHERE bill_id = ? AND Item_ID = ?";
    	    PreparedStatement pstmt = con.prepareStatement(sql);
    	    pstmt.setInt(1, quantity);
    	    pstmt.setInt(2, price);
    	    pstmt.setInt(3, billID);
    	    pstmt.setInt(4, itemID);
    	            
    	    int rowsUpdated = pstmt.executeUpdate();
    	            
    	    if (rowsUpdated > 0) {
    	        return true;
    	    }
    	} catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; 
    }
    
    //kiểm tra bàn chưa thanh toán nhưng gọi thêm món 
    public int getOpenBillIDForTable(int tableID) {
        try {
        	connect connector = new connect();
	        Connection con = connector.connection;
	        String sql = "SELECT bill_ID FROM bill WHERE Table_ID = ? AND Status = 0";
	        PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            
            ResultSet rs = pstmt.executeQuery();            
            if (rs.next()) {
                return rs.getInt("Bill_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    
    //kiểm tra món đã có trong bill hay chưa
    public boolean hasItemInBill(int billID, int itemID) {        
        try{
        	connect connector = new connect();
	        Connection con = connector.connection;
	        String sql = "SELECT * FROM order_details WHERE Bill_ID = ? AND Item_ID = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, billID);
            pstmt.setInt(2, itemID);
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updateBillTotal(int billID) {
        try{
        	connect connector = new connect();
	        Connection con = connector.connection;
	        String sql = "UPDATE bill AS b "
	        		+ "SET b.Total = ("
	        		+ "    SELECT SUM(total_price) "
	        		+ "    FROM order_details"
	        		+ "    WHERE Bill_ID = ?) "
	        		+ "WHERE b.Bill_ID = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, billID);
            pstmt.setInt(2, billID);
            
            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; 
    }
}
