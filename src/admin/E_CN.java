package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connectDTB.connect;
public class E_CN {
	private static connect conn = new connect();
	private static Connection connection = (Connection) conn.connection;
  public static void insertData(String empID, String empName, String empPhone, String empAddress, String empPosition, String empPassword) {
      try {
          
          String query = "INSERT INTO employment(Emp_ID, Name, Phone, Address, Position, Password) VALUES(?, ?, ?, ?, ?, ?)";
          PreparedStatement pstmt = connection.prepareStatement(query);
          pstmt.setString(1, empID);
          pstmt.setString(2, empName);
          pstmt.setString(3, empPhone);
          pstmt.setString(4, empAddress);
          pstmt.setString(5, empPosition);
          pstmt.setString(6, empPassword);
          pstmt.executeUpdate();
          connection.close();
      } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Khong them duoc!");
      }
  }

  public static void deleteData(String empID) {
      try {
        
          String query = "DELETE FROM employment WHERE Emp_ID=?";
          PreparedStatement pstmt = connection.prepareStatement(query);
          pstmt.setString(1, empID);
          pstmt.executeUpdate();
          connection.close();
      } catch (SQLException ex) {
    	  JOptionPane.showMessageDialog(null, "Khong xoa duoc!");
      }
  }
  
  public static void updateData(String empID, String empName, String empPhone, String empAddress, String empPosition, String empPassword) {
      try {
         
          String query = "UPDATE employment SET Name=?, Phone=?, Address=?, Position=?, Password=? WHERE Emp_ID=?";
          PreparedStatement pstmt = connection.prepareStatement(query);
          pstmt.setString(1, empName);
          pstmt.setString(2, empPhone);
          pstmt.setString(3, empAddress);
          pstmt.setString(4, empPosition);
          pstmt.setString(5, empPassword);
          pstmt.setString(6, empID);
          pstmt.executeUpdate();
          connection.close();
      } catch (SQLException ex) {
    	  JOptionPane.showMessageDialog(null, "Khong sua duoc!");
      }
  }
}