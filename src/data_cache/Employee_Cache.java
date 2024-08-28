package data_cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDTB.connect;
import security.Crypt;

public class Employee_Cache {
    public static List<Integer> Emp_ID = new ArrayList<>();
    public static List<String> Emp_Name = new ArrayList<>();
    public static List<String> Emp_Phone = new ArrayList<>();
    public static List<String> Emp_Address = new ArrayList<>();
    public static List<String> Emp_Position = new ArrayList<>();
    public static List<String> Emp_Pass = new ArrayList<>();
    
    public  Employee_Cache() throws SQLException  {
        connect connector = new connect();
        Connection conn = connector.connection;
        if (conn != null) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Employment";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Emp_ID.add(resultSet.getInt("Emp_ID"));
                Emp_Name.add(resultSet.getString("Name"));
                Emp_Phone.add(resultSet.getString("Phone"));
                Emp_Address.add(resultSet.getString("Address"));
                Emp_Position.add(resultSet.getString("Position"));
                String pasString = "";
                try {
					pasString = Crypt.decrypt(resultSet.getString("Password"));
				} catch (Exception e) {
					// TODO: handle exception
				}
                Emp_Pass.add(pasString);
            }
            resultSet.close();
            stmt.close();
            if (conn != null) {
                conn.close();
            }
        }
    }
    public static void reloadData() {
        Emp_ID.clear();
        Emp_Name.clear();
        Emp_Phone.clear();
        Emp_Address.clear();
        Emp_Position.clear();
        Emp_Pass.clear();
        try {
            connect connector = new connect();
            Connection conn = connector.connection;
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM Employment";
                ResultSet resultSet = stmt.executeQuery(sql);
                while (resultSet.next()) {
                    Emp_ID.add(resultSet.getInt("Emp_ID"));
                    Emp_Name.add(resultSet.getString("Name"));
                    Emp_Phone.add(resultSet.getString("Phone"));
                    Emp_Address.add(resultSet.getString("Address"));
                    Emp_Position.add(resultSet.getString("Position"));
                    String pasString = "";
                    try {
                        pasString = Crypt.decrypt(resultSet.getString("Password"));
                    } catch (Exception e) {
                        // Xử lý ngoại lệ khi giải mã mật khẩu
                    }
                    Emp_Pass.add(pasString);
                }
                resultSet.close();
                stmt.close();
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

}