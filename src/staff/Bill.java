package staff;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDTB.connect;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class Bill extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel  panel;
    private static JTable table;
    private static DefaultTableModel model;
    private JButton edit, issue, btnUpdate;
    private int discountPrice, finalPrice;
    private JComboBox cbx;
    private int SelectedValue;
	/**
	 * Create the panel.
	 */
	public Bill() {
		 	
		 	setBounds(0, 0, 1540, 815);
	        setBorder(new EmptyBorder(5, 5, 5, 5));
	        setLayout(null);

	        model = new DefaultTableModel();
	        
	        panel = new JPanel();
	        panel.setBackground(Color.WHITE);
	        panel.setBounds(766, 263, 554, 507);
	        add(panel);
	        panel.setLayout(null);
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(46, 263, 681, 507);
	        add(scrollPane);
	        scrollPane.setEnabled(false);
	        scrollPane.setPreferredSize(new Dimension(600, 400)); 
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBounds(1354, 263, 112, 507);
	        add(panel_1);
	        panel_1.setBackground(Color.WHITE);
	        panel_1.setLayout(null);
	       
	        issue = new JButton("Xuất");
	        issue.addActionListener(this);
	        
	        issue.setBounds(16, 33, 90, 54);
	        panel_1.add(issue);
	        
	        edit = new JButton("Xem");
	        edit.setBounds(16, 124, 90, 54);
	        panel_1.add(edit);
	        edit.addActionListener(this);
	        issue.addActionListener(this); 
	        JLabel lbt = new JLabel("Danh sách bàn chưa thanh toán: ");
	        lbt.setSize(230, 37);
	        lbt.setLocation(6, 16);
	        lbt.setBackground(Color.LIGHT_GRAY);
	        cbx = new JComboBox<Object>();
	        cbx.setEditable(true);
	        cbx.setSize(110, 25);
	        cbx.setLocation(236, 21);
	        
	        
	        ArrayList<String> list = LoadDataToCombobox();
	        for(String item : list) {
	        	cbx.addItem(item.toString());
	        }        
	        
	        JPanel panel_2 = new JPanel();
	        panel_2.setBackground(Color.WHITE);
	        panel_2.setBounds(6, 111, 554, 64);
	        add(panel_2);
	        panel_2.add(lbt);
	        panel_2.add(cbx);
	        panel_2.setLayout(null);

	        updateTable();
	}
	
	public static ArrayList<String> LoadDataToCombobox(){
    	ArrayList<String> list = new ArrayList<String>();
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/data"; 
            Connection con = DriverManager.getConnection(url, "root", "");
            String sql = "SELECT Table_ID FROM bill Where Status = false";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
            	list.add(rs.getString("Table_ID"));
            }   
    	} catch (Exception e) { }
		return list;
    	
    }

//	 public void createPDF(String[] rowData, int SelectedRow, String[] content, String[] name, String[] price ,String[] quantity, Object Total, String[] total_price, int finalPrice, int discountPrice) {
//
//	        try {
//	            PDDocument document = new PDDocument();
//	            PDPage page = new PDPage();
//	            document.addPage(page);
//	            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//	            PDFont font = null;
//	            try {
//	                font = PDType0Font.load(document, new File("D:\\PBL\\PBL3\\src\\font\\arial-unicode-ms.ttf"));
//	            } catch (IOException e) {
//	            	JOptionPane.showMessageDialog(null, "Not found! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//	            }
//	            float fontSize = 11;
//	            float leading = 1.5f * fontSize;
//	            float margin = 50;
//	            float yStart = page.getMediaBox().getHeight() - margin;
//	            float yPosition = yStart;
//	            String separator = "-------------------------------------------------------------";
//	            String[] combine = new String[content.length];
//	            for(int i = 0; i < content.length; i++) {
//	            	if (i < rowData.length) {
//	                    combine[i] = content[i] + ": " + rowData[i];
//	                } else {
//	                    combine[i] = content[i] + ":";
//	                }
//	            }
//	            float textWidth = font.getStringWidth("PHIẾU THANH TOÁN") / 1000 * 16;
//	            float centerX = (page.getMediaBox().getWidth() - textWidth) / 2;
//	            contentStream.beginText();
//	            contentStream.setFont(font, 16);
//	            contentStream.newLineAtOffset(centerX + 30, yPosition); 
//	            contentStream.showText("LAVA RESTAURANT");
//	            contentStream.endText();
//	            yPosition -= leading * 2;
//	            
//	            contentStream.beginText();
//	            contentStream.setFont(font, 14); 
//	            contentStream.newLineAtOffset(centerX + 30, yPosition); 
//	            contentStream.showText("PHIẾU THANH TOÁN");
//	            contentStream.endText();
//	            yPosition -= leading * 2; 
//	            
//	            
//	            for (String data : combine) {
//	            	contentStream.beginText();
//	                contentStream.setFont(font, fontSize);
//	                contentStream.newLineAtOffset(centerX, yPosition);
//	                contentStream.showText(data);
//	                contentStream.endText();
//	                yPosition -= leading;
//	           }
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText("Tên món                 Đơn giá    SL     Thành tiền");
//	            contentStream.endText();
//	            yPosition -= leading;
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText(separator);
//	            contentStream.endText();
//	            yPosition -= leading;
//	           
//
//	            String[] combineFood = new String[name.length];
//
//	            for (int i = 0; i < name.length; i++) {
//	                combineFood[i] = String.format("%-5s %-23s %-8s", (i + 1) + ".", name[i], price[i]) + String.format("%-2s %10s", quantity[i], total_price[i]);
//	            }
//	            for (String data : combineFood) {
//	                contentStream.beginText();
//	                contentStream.setFont(font, fontSize);
//	                contentStream.newLineAtOffset(centerX, yPosition);
//	                contentStream.showText(data);
//	                contentStream.endText();
//	                yPosition -= leading;
//	            }
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText(separator);
//	            contentStream.endText();
//	            yPosition -= leading;
//	            
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText("Tổng   :" + String.format("%55s", Total.toString()));
//	            contentStream.endText();
//	            yPosition -= leading;
//	            
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText("Giảm   :" + String.format("%55s", "-" + discountPrice));
//	            contentStream.endText();
//	            yPosition -= leading;
//	            
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText("T.toán :" + String.format("%55s", finalPrice));
//	            contentStream.endText();
//	            yPosition -= leading;
//	            
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText(separator);
//	            contentStream.endText();
//	            yPosition -= leading;
//	            
//	            contentStream.beginText();
//	            contentStream.setFont(font, fontSize);
//	            contentStream.newLineAtOffset(centerX, yPosition);
//	            contentStream.showText("        Xin cảm ơn và hẹn gặp lại quý khách!");
//	            contentStream.endText();			
//	            yPosition -= leading;
//	            
//	            // Đóng content stream
//	            contentStream.close();
//	            // Lưu tài liệu PDF ra file
//	            document.save(table.getValueAt(SelectedRow, 0).toString()+".pdf");
//	            // Đóng tài liệu PDF
//	            document.close();
//	            JOptionPane.showMessageDialog(null, "Xuất hoá đơn thành công.");
//	        } catch (Exception e) {
//	            JOptionPane.showMessageDialog(null, "Không thể xuất hoá đơn: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//	        }
//	    }
	 public static void updateTable() {
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
	            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    public void editBill(Object[] rowData, Object billID) {
	    	panel.removeAll();
	        JTextField[] textFields = new JTextField[6];
	        JTextArea textArea = new JTextArea();
	        String[] columnNames = {"Mã bill", "Thời gian", "Mã bàn", "Mã NV", "Tổng tiền",  "Trạng thái", "Món"};
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
	            	textArea.setEditable(false);
	            	panel.add(textArea);
	            } else {
	            	textFields[i] = new JTextField();
	            	textFields[i].setBounds(120, 10 + 25 * i, 200, 20);
	            	textFields[i].setEditable(false);
	            	if(i < rowData.length) {
	            		textFields[i].setText(rowData[i].toString());
	            }
	            panel.add(textFields[i]);
	            }
	        }
	        JTextField discount = new JTextField();
	        JLabel label1 = new JLabel("Giảm giá(%):");
	        label1.setBounds(10, 220, 100, 20);
	        discount.setBounds(120,220,200,20);
	        discount.setText("0");
	        
	        panel.add(label1);
	        panel.add(discount);
	        
	        btnUpdate = new JButton("Thanh toán");
	        btnUpdate.setHorizontalAlignment(SwingConstants.RIGHT);
	        btnUpdate.setBounds(100, 300, 120, 60);
	        panel.add(btnUpdate);       
	        btnUpdate.addActionListener((ActionListener) this);
	        btnUpdate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	double dc = Double.parseDouble(discount.getText());
	                try {
	                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
	                    String sqlBill = "UPDATE bill SET Status = ?, Total = ? WHERE Bill_ID=?";
	                    PreparedStatement pstmtBill = con.prepareStatement(sqlBill);
	                    pstmtBill.setBoolean(1, true);
	                    discountPrice = (int) ((Integer.parseInt(textFields[4].getText()))*(dc/100));
	                    finalPrice = (int) (Integer.parseInt(textFields[4].getText()) - discountPrice);
	                    pstmtBill.setInt(2, finalPrice);
	                    pstmtBill.setString(3, textFields[0].getText()); 
	                    int rowsAffectedBill = pstmtBill.executeUpdate();
	                    pstmtBill.close();
	                   
	                    if (rowsAffectedBill > 0) {
	                        JOptionPane.showMessageDialog(null, "Xác nhận thanh toán thành công.");
	                        connect connector = new connect();
	        				Connection conn = connector.connection;
	        				Statement stmt = null;
	        				String updateTableQuery = "UPDATE tables SET Status = 0 WHERE Table_ID = " + SelectedValue; 
	  		        	  try {
	  		        		  stmt = conn.createStatement();
	  		        		  stmt.executeUpdate(updateTableQuery);
	  		        		JOptionPane.showMessageDialog(null, "da ok.");
	  		        	  } catch (SQLException e1) {
	  						// TODO Auto-generated catch block
	  						e1.printStackTrace();
	  		        	  }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Không thể xác nhận thanh toán.");
	                    }
	                    con.close();
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        revalidate();
	        repaint();
	        
	    }
	    
	    public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == edit) {
    			SelectedValue = Integer.parseInt((String) cbx.getSelectedItem());
    			try {
    				String selectRow = null;
    	    		Class.forName("com.mysql.jdbc.Driver");
    	            String url = "jdbc:mysql://localhost:3306/data"; 
    	            Connection con = DriverManager.getConnection(url, "root", "");
    	            String sql = "SELECT * FROM bill Where Status = false and Table_ID = ?";
    	            PreparedStatement pstmt = con.prepareStatement(sql);
    	            pstmt.setInt(1, SelectedValue);
    	            ResultSet rs = pstmt.executeQuery();
    	            if(rs.next()) {
    	            	selectRow = rs.getString("bill_ID"); 
    	                Object[] rowData = new Object[model.getColumnCount()];
    	                for (int i = 0; i < model.getColumnCount(); i++) {
    	                    rowData[i] = rs.getObject(i + 1); 
    	                }
    	                Object billID = rowData[0];
    	                Component[] components = getComponents();
    	                for (Component component : components) {
    	                    if (component instanceof JTextField) {
    	                        remove(component);
    	                    }
    	                }
    	                editBill(rowData, billID);
    	            }   
    	            
    	    	} catch (Exception e1) { }
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

		public void actionPerformed1(ActionEvent e) {
			if (e.getSource() == edit) {
	            int selectedRow = table.getSelectedRow();
	            if (selectedRow != -1) {
	                Object[] rowData = new Object[model.getColumnCount()];
	                for (int i = 0; i < model.getColumnCount(); i++) {
	                    rowData[i] = model.getValueAt(selectedRow, i);
	                }
	                Object billID = rowData[0];
	                Component[] components = getComponents();
	                for (Component component : components) {
	                    if (component instanceof JTextField) {
	                        remove(component);
	                    }
	                }
	                editBill(rowData, billID);
				
	        }
			}  
		        if (e.getSource() == issue) {
		       
		        	int selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		                String[] rowData = new String[4];
		                String[] content = {"Mã bill", "Thời gian","Bàn", "Mã NV"};
		                for (int i = 0; i < 4; i++) {
		                    rowData[i] = table.getValueAt(selectedRow, i).toString();
		                }
		                Object billID = table.getValueAt(selectedRow, 0);
		                Object Total = table.getValueAt(selectedRow, 4);
		                String[] name = null;
		                String[] quantity = null;
		                String[] price = null;
		                String[] total_price = null;
		                try {
		                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
		                    String query = "SELECT fdc.Name, fdc.Price, od.Quantity, od.total_price "
		                                 + "FROM food_drink AS fdc "
		                                 + "JOIN order_details AS od ON fdc.ID = od.item_ID "
		                                 + "JOIN bill AS b ON od.bill_ID = b.bill_ID "
		                                 + "WHERE b.bill_ID = ?";
		                    PreparedStatement pstmt = con.prepareStatement(query);
		                    pstmt.setString(1, billID.toString());
		                    ResultSet rs = pstmt.executeQuery();
		                    // Đếm số lượng hàng trong ResultSet
		                    rs.last(); // Di chuyển tới hàng cuối cùng
		                    int numRows = rs.getRow(); // Lấy chỉ số hàng hiện tại
		                    rs.beforeFirst(); // Di chuyển về hàng đầu tiên
		                    // Khởi tạo mảng với số phần tử là số hàng trong ResultSet
		                    name = new String[numRows];
		                    quantity = new String[numRows];
		                    price = new String[numRows]; 
		                    total_price = new String[numRows];
		                    int index = 0;
		                    while (rs.next()) {
		                        name[index] = rs.getString("Name");
		                        quantity[index] = rs.getString("Quantity");
		                        price[index] = rs.getString("Price");
		                        total_price[index] = rs.getString("total_price");
		                        index++;
		                    }
		                    rs.close();
		                    pstmt.close();
		                    con.close();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                }
		                //createPDF(rowData, selectedRow, content, name, price , quantity, Total, total_price, finalPrice, discountPrice);
		            } else {
		                JOptionPane.showMessageDialog(null, "Please select a row to export.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		     
		        }	
		    }

}
