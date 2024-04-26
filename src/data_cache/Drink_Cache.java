package data_cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import connectDTB.connect;

public class Drink_Cache {
    public static List<Integer> Drink_ID = new ArrayList<>();
    public static List<String> Drink_Name = new ArrayList<>();
    public static List<Integer> Drink_Price = new ArrayList<>();
    public static List<Integer> Drink_Quantity = new ArrayList<>();

    public Drink_Cache() throws SQLException {
        connect connector = new connect();
        Connection conn = connector.connection;
        if (conn != null) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM food_drink WHERE classify = 1";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Drink_ID.add(resultSet.getInt("ID"));
                Drink_Name.add(resultSet.getString("Name"));
                Drink_Price.add(resultSet.getInt("Price"));
                Drink_Quantity.add(resultSet.getInt("Quantity"));
            }
            resultSet.close();
            stmt.close();
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static void addDrink(int id, String name, int price, int quantity) throws SQLException {
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
        Drink_ID.add(id);
	    Drink_Name.add(name);
	    Drink_Price.add(price);
	    Drink_Quantity.add(quantity);
    }
	
	public static void updateDrink(int id, int newId, String newName, int newPrice, int newQuantity) throws SQLException {
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

	    int index = Drink_ID.indexOf(id);
	    if (index != -1) { // Kiểm tra xem mục có tồn tại trong danh sách không
	        Drink_ID.set(index, newId); // Cập nhật thông tin
	        Drink_Name.set(index, newName);
	        Drink_Price.set(index, newPrice);
	        Drink_Quantity.set(index, newQuantity);
	    }
	}

	
	public static void deleteDrink(int id) throws SQLException {
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
	    int index = Drink_ID.indexOf(id);
        if (index != -1) { // Kiểm tra xem mục có tồn tại trong danh sách không
            Drink_ID.remove(index); // Xóa khỏi danh sách
            Drink_Name.remove(index);
            Drink_Price.remove(index);
            Drink_Quantity.remove(index);
        }
	}
	
	}
