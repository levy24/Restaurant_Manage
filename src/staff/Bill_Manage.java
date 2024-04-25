package staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Bill_Manage extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panel;
    private JTable table;
    private DefaultTableModel model;
    private JButton edit, issue, addBtn, btnUpdate;

    public Bill_Manage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 800, 400); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        model = new DefaultTableModel();
        
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(6, 111, 554, 277);
        contentPane.add(panel);
        panel.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 0, 764, 112);
        contentPane.add(scrollPane);
        scrollPane.setEnabled(false);
        scrollPane.setPreferredSize(new Dimension(600, 400)); 
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(559, 111, 112, 261);
        contentPane.add(panel_1);
        panel_1.setBackground(Color.WHITE);
        panel_1.setLayout(null);
        
        addBtn = new JButton("Add");
        addBtn.setBounds(16, 109, 90, 29);
        panel_1.add(addBtn);
        
        issue = new JButton("Issue");
        issue.setBounds(16, 139, 90, 29);
        panel_1.add(issue);
        
        edit = new JButton("Edit");
        edit.setBounds(16, 169, 90, 29);
        panel_1.add(edit);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(16, 201, 88, 29);
        panel_1.add(btnUpdate);
        
        btnUpdate.addActionListener(this);
        edit.addActionListener(this);
        issue.addActionListener(this);
        addBtn.addActionListener(this);
        
        updateTable();
    }

    private void updateTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/data"; 
            Connection con = DriverManager.getConnection(url, "root", "");
            String sql = "SELECT * FROM bill ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            model.setRowCount(0); 
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnLabel(columnIndex));
                if (columnIndex == 2) {
                    table.getColumnModel().getColumn(columnIndex - 1).setWidth(400);
                }  
            }
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
          
                model.addRow(row);
            } 
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bill_Manage frame = new Bill_Manage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void editBill(Object[] rowData, Object billID) {
    	panel.removeAll();
        JTextField[] textFields = new JTextField[7];
        JTextArea textArea = new JTextArea();
        String[] columnNames = {"Bill_ID", "Time", "Total", "Status", "Phone", "Table_ID", "Emp_ID", "List"};
        for (int i = 0; i < columnNames.length; i++) {
            JLabel label = new JLabel(columnNames[i] + ":");
            label.setBounds(10, 10 + 25 * i, 100, 20);
            panel.add(label);
            if(i == columnNames.length - 1) {
            	try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
                    String query =
                    		"SELECT fdc.Name, od.Quantity "
                    		+ "FROM food_drink AS fdc "
                    		+ "JOIN order_details AS od ON fdc.id = od.item_ID "
                    		+ "JOIN bill AS b ON od.bill_ID = b.bill_ID "
                    		+ "WHERE b.bill_ID = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, billID.toString());
                   
                    ResultSet rs = pstmt.executeQuery();
                    StringBuilder result = new StringBuilder();
                    while (rs.next()) {
                        result.append(rs.getString("Name")).append(" ");
                        result.append(rs.getString("Quantity")).append("\n");
                    }
                    textArea.setText(result.toString());
                    rs.close();
                    pstmt.close();
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            	textArea.setBounds(120,10 + 25*i, 200, 50);
            	textArea.setBackground(Color.white);
            	panel.add(textArea);
            } else {
            	textFields[i] = new JTextField();
            	textFields[i].setBounds(120, 10 + 25 * i, 200, 20);
            	if(i < rowData.length) {
            		textFields[i].setText(rowData[i].toString());
            }
            panel.add(textFields[i]);
            }
        }
//        btnUpdate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
//                    String sql = "UPDATE bill SET Time=?, Total=?, Status=?, Phone=?, Table_ID=?, Emp_ID=? WHERE Bill_ID=?";
//                    PreparedStatement pstmt = con.prepareStatement(sql);
//                    for (int i = 1; i < columnNames.length; i++) { 
//                        pstmt.setString(i, textFields[i].getText());
//                    }
//                    pstmt.setString(textFields.length, textFields[0].getText());
//                    int rowsAffected = pstmt.executeUpdate();
//                    if (rowsAffected > 0) {
//                        JOptionPane.showMessageDialog(null, "Data updated successfully.");
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Data update failed.");
//                    }
//                    pstmt.close();
//                    con.close();
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
                    String sqlBill = "UPDATE bill SET Time=?, Total=?, Status=?, Phone=?, Table_ID=?, Emp_ID=? WHERE Bill_ID=?";
                    String sqlFoodDrink = "UPDATE food_drink SET Quantity=? WHERE Name=?";
                    PreparedStatement pstmtBill = con.prepareStatement(sqlBill);
                    for (int i = 1; i < columnNames.length; i++) { 
                        pstmtBill.setString(i, textFields[i].getText());
                    }
                    pstmtBill.setString(columnNames.length, textFields[0].getText());
                    int rowsAffectedBill = pstmtBill.executeUpdate();
                    pstmtBill.close();
                    PreparedStatement pstmtFoodDrink = con.prepareStatement(sqlFoodDrink);
                    pstmtFoodDrink.setString(1, textFields[8].getText()); // Quantity
                    pstmtFoodDrink.setString(2, textFields[7].getText()); // Name
                    int rowsAffectedFoodDrink = pstmtFoodDrink.executeUpdate();
                    pstmtFoodDrink.close();
                    if (rowsAffectedBill > 0 && rowsAffectedFoodDrink > 0) {
                        JOptionPane.showMessageDialog(null, "Data updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data update failed.");
                    }
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        contentPane.revalidate();
        contentPane.repaint();
    }

    public class IssueWindow extends JFrame {
        private JPanel contentPane;
        private JPanel orderPane;
        private Connection con;
        public IssueWindow(Object[] rowData, String[] contents, Connection con, String orderDetail_ID, String foodName, String drinkName) {
            this.con = con;
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(new GridLayout(rowData.length, 1, 5, 5));
            for (int i = 0; i < rowData.length + 2; i++) {
                String text = "";
                String list = "";
                if (contents[i].equals("Food")) {
                    list = foodName;
                } else if (contents[i].equals("Drink")) {
                    list = drinkName;
                } else {
                    text = contents[i] + ": " + rowData[i].toString();
                }
                
                JLabel label = new JLabel(text + "\n");
                JLabel label_list = new JLabel(list + "\n");
                orderPane.add(label_list);
                contentPane.add(label);
                
            }
            JButton btnIssue = new JButton("Issue");
            btnIssue.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Đã xuất bill");
                    dispose();
                }
            });
            contentPane.add(btnIssue);
            contentPane.add(orderPane);
            
        }
        @Override
        public void dispose() {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error closing database connection: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            super.dispose();
        }
    }
    public int getCurrentBillID() {
        int currentBillID = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
            String sql = "SELECT MAX(bill_ID) FROM bill";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                currentBillID = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return currentBillID;
    }

    
    public void addBill() {
    	int currentBillID = getCurrentBillID();
    	currentBillID++;
    	LocalDateTime currentDateTime = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
    	panel.removeAll();
        JTextField[] textFields = new JTextField[7];
        String[] columnNames = {"Bill_ID", "Time", "Total", "Status", "Cus_Phone", "Table_ID", "Emp_ID"};
        for (int i = 0; i < columnNames.length; i++) {
            JLabel label = new JLabel(columnNames[i] + ":");
            label.setBounds(10, 10 + 25 * i, 100, 20);
            panel.add(label);
            textFields[i] = new JTextField();
            textFields[i].setBounds(120, 10 + 25 * i, 200, 20);
            panel.add(textFields[i]);
            if (i == 0) {
            	textFields[i].setText(String.valueOf(currentBillID));
            	textFields[i].setEditable(false); 
            }
            if(i == 1) {
            	textFields[i].setText(formattedDateTime);
            	textFields[i].setEditable(false);
            }
        panel.add(textFields[i]);
        }
        JButton btnAddBill = new JButton("Add Bill");
        JButton btnCancel = new JButton("Cancel");
        btnAddBill.setBounds(10, 10 + 25 * columnNames.length, 100, 20);
        btnAddBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] values = new String[7];
                    for (int i = 0; i < textFields.length; i++) {
                        values[i] = textFields[i].getText();
                    }
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
                    String sql = "INSERT INTO bill (Bill_ID, Time, Total, Status, Cus_Phone, Table_ID, Emp_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    for (int i = 0; i < values.length; i++) {
                        pstmt.setString(i + 1, values[i]);
                    }
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "New bill added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add new bill.");
                    }
                    pstmt.close();
                    con.close();
                    updateTable(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.setBounds(120, 10 + 25 * (columnNames.length), 100, 20);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        panel.add(btnAddBill);
        panel.add(btnCancel);
        panel.revalidate();
        panel.repaint();
        
    }

  	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == edit) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = model.getValueAt(selectedRow, i);
                }
                Object billID = rowData[0];
                Component[] components = contentPane.getComponents();
                for (Component component : components) {
                    if (component instanceof JTextField) {
                        contentPane.remove(component);
                    }
                }
                editBill(rowData, billID);
			
        }
		}  
	        if (e.getSource() == issue) {
	            int selectedRow1 = table.getSelectedRow();
	            if (selectedRow1 != -1) {
	                Object[] rowData = new Object[model.getColumnCount()];
	                String[] contents = {"Bill ID", "Time", "Total", "Status", "Quantity", "Phone", "Table", "Employee", "Order", "Food", "Drink"};
	                for (int i = 0; i < model.getColumnCount(); i++) {
	                    rowData[i] = model.getValueAt(selectedRow1, i);
	                }
	                Connection con = null;
	                String orderDetail_ID = rowData[model.getColumnCount() - 1].toString();
	                try {
	                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
	                    String query = "SELECT Food_category.Name AS foodName, Drink_category.Name AS drinkName FROM Order_detail "
	                            + "JOIN Food_category ON Order_detail.Food_ID = Food_category.Food_ID "
	                            + "JOIN Drink_category ON Order_detail.Drink_ID = Drink_category.Drink_ID "
	                            + "WHERE Order_detail.Order_ID = ?";

	                    PreparedStatement pstmt = con.prepareStatement(query);
	                    pstmt.setString(1, orderDetail_ID);
	                    ResultSet rs = pstmt.executeQuery();
	                    if (rs.next()) {
	                        String foodName = rs.getString("foodName");
	                        String drinkName = rs.getString("drinkName");
	                        new IssueWindow(rowData, contents, con, orderDetail_ID, foodName, drinkName).setVisible(true);
	                    }
	                    rs.close();
	                    pstmt.close();
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                } finally {
	                    if (con != null) {
	                        try {
	                            con.close();
	                        } catch (SQLException ex) {
	                            ex.printStackTrace();
	                        }
	                    }
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select a row to issue.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        if(e.getSource() == addBtn) {
	        	addBill();
	        }
	    }
  	}
