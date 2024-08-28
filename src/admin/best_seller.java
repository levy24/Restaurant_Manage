package admin;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;
import connectDTB.connect;
public class best_seller extends JPanel {
    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    /**
     * Create the panel.
     */
    public best_seller() throws SQLException {
        setBounds(0, 0, 1540, 815);
        setLayout(null);
        
        Font font = new Font("Times New Roman", Font.PLAIN, 16); 
        connect conn = new connect();
        Connection connection = conn.connection;
        String query = "SELECT fdc.Name, SUM(od.Quantity) AS totalQuantity " +
                "FROM food_drink AS fdc " +
                "JOIN order_details AS od ON fdc.ID = od.Item_ID " +
                "GROUP BY fdc.Name " +
                "ORDER BY totalQuantity DESC";
        PreparedStatement prp;
        ResultSet resultSet = null;
        Map<String, Integer> itemCountMap = new HashMap<>();
        prp = (PreparedStatement) connection.prepareStatement(query);
        resultSet = prp.executeQuery();
        while (resultSet.next()) {
            String itemName = resultSet.getString("Name");
            int quantity = resultSet.getInt("totalQuantity"); 
            
            itemCountMap.put(itemName, itemCountMap.getOrDefault(itemName, 0) + quantity);
        }
        model = new DefaultTableModel();
        model.addColumn("Tên món được gọi");
        model.addColumn("Số lượng đã gọi");
        
        // Populate the data model with data
        itemCountMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(entry -> model.addRow(new Object[] { entry.getKey(), entry.getValue() }));
        table = new JTable(model);
        table.setEnabled(false);
        scrollPane = new JScrollPane(table);
        table.setFont(font);
        scrollPane.setBounds(10, 10, 1520, 795); 
        add(scrollPane);
    }
}