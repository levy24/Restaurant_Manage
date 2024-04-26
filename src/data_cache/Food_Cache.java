package data_cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import connectDTB.connect;

public class Food_Cache {
		public static List<Integer> FID = new ArrayList<>();
		public static List<String>  FName = new ArrayList<>();;
		public static List<Integer>  FPrice = new ArrayList<>();;
		public static List<Integer>  FQuantity = new ArrayList<>();
		public Food_Cache() throws SQLException {	
			connect connector = new connect();
	        Connection conn = connector.connection;
			if( conn != null) {
			
				Statement stmt = conn.createStatement();
	        	String sql = "SELECT * FROM food_drink WHERE classify = 0";
	        	ResultSet resultSet = stmt.executeQuery(sql);
	        	while (resultSet.next()) {
	        		FID.add(resultSet.getInt("ID"));
	        		FName.add(resultSet.getString("Name"));
	        		FPrice.add(resultSet.getInt("Price"));
	        		FQuantity.add(resultSet.getInt("Quantity"));
	        	}	
	        resultSet.close();
	        stmt.close();
	        if(conn != null) {
	        	conn.close();
	        }
			}
		}
		
		public static void addFood(int id, String name, int price, int quantity) throws SQLException {
	        connect connector = new connect();
	        Connection conn = connector.connection;
	        if (conn != null) {
	            String sql = "INSERT INTO food_drink (ID, Name, Price, Quantity) VALUES (?, ?, ?, ?)";
	            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	            preparedStatement.setInt(1, id);
	            preparedStatement.setString(2, name);
	            preparedStatement.setInt(3, price);
	            preparedStatement.setInt(4, quantity);
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            conn.close();
	        }
	        FID.add(id);
		    FName.add(name);
		    FPrice.add(price);
		    FQuantity.add(quantity);
	    }
		
		public static void updateFood(int id, int newId, String newName, int newPrice, int newQuantity) throws SQLException {
		    connect connector = new connect();
		    Connection conn = connector.connection;
		    if (conn != null) {
		        String sql = "UPDATE food_drink SET ID = ?, Name = ?, Price = ?, Quantity = ? WHERE ID = ?";
		        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		        preparedStatement.setInt(1, newId);
		        preparedStatement.setString(2, newName);
		        preparedStatement.setInt(3, newPrice);
		        preparedStatement.setInt(4, newQuantity);
		        preparedStatement.setInt(5, id);
		        preparedStatement.executeUpdate();
		        preparedStatement.close();
		        conn.close();
		    }

		    int index = FID.indexOf(id);
		    if (index != -1) { // Kiểm tra xem mục có tồn tại trong danh sách không
		        FID.set(index, newId); // Cập nhật thông tin
		        FName.set(index, newName);
		        FPrice.set(index, newPrice);
		        FQuantity.set(index, newQuantity);
		    }
		}

		
		public static void deleteFood(int id) throws SQLException {
		    connect connector = new connect();
		    Connection conn = connector.connection;
		    if (conn != null) {
		        String sql = "DELETE FROM food_drink WHERE ID = ?";
		        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		        preparedStatement.setInt(1, id);
		        preparedStatement.executeUpdate();
		        preparedStatement.close();
		        conn.close();
		    }
		    int index = FID.indexOf(id);
	        if (index != -1) { 
	            FID.remove(index); 
	            FName.remove(index);
	            FPrice.remove(index);
	            FQuantity.remove(index);
	        }
		}

		
}
