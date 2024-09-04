package staff;

import java.awt.BorderLayout;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDTB.connect;
import font.RoundedBorder;
import login.dangnhap1;

import java.awt.Font;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class Bill extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel  panel;
    private static JTable table;
    private static DefaultTableModel model;
    private JButton edit, issue, btnUpdate;
    private static int discountPrice = 0, finalPrice = 0;
    private static JComboBox cbx;
    private int SelectedValue, empCash;
    private static int curr;
    private static int status = 0;
    private JTextField txtFind;
    public Bill() {
	 	
	 	setBounds(0, 0, 1540, 815);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(new Color(255, 222, 173));
        setLayout(null);

        model = new DefaultTableModel();
        
        panel = new JPanel();
        panel.setBackground(new Color(255, 250, 240));
        panel.setBounds(759, 109, 700, 664);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        add(panel);
        panel.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(255, 250, 240));
        scrollPane.setBounds(10, 60, 611, 655);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        
        scrollPane.setEnabled(false);
        scrollPane.setPreferredSize(new Dimension(600, 400)); 
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(1349, 48, 110, 51);
        panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
        add(panel_1);
        panel_1.setBackground(new Color(255, 250, 240));
        panel_1.setLayout(null);
       
  
        edit = new JButton("Xem");
        edit.setBackground(new Color(255, 255, 255));
        edit.setBorder(new RoundedBorder(20));
        edit.setForeground(new Color(75, 0, 130));
        edit.setBorder(new LineBorder(new Color(64, 0, 128), 1, true));
        edit.setFont(new Font("Times New Roman", Font.BOLD, 25));
        edit.setBounds(10, 10, 90, 35);
        panel_1.add(edit);
        edit.addActionListener(this);
        JLabel lbt = new JLabel("Danh sách bàn chưa thanh toán: ");
        lbt.setBounds(31, 10, 398, 37);
        lbt.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lbt.setForeground(new Color(75, 0, 130));
        cbx = new JComboBox<Object>();
        cbx.setBounds(454, 10, 95, 30);
        cbx.setForeground(new Color(75, 0, 130));
        cbx.setBorder(BorderFactory.createLineBorder(Color.black));
        cbx.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        cbx.setEditable(true);
        
        ArrayList<String> list = LoadDataToCombobox();
        for(String item : list) {
        	cbx.addItem(item.toString());
        }
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 250, 240));
        panel_2.setBounds(759, 48, 580, 51);
        panel_2.setBorder(BorderFactory.createLineBorder(Color.black));
        add(panel_2);
        panel_2.setLayout(null);
        panel_2.add(lbt);
        panel_2.add(cbx);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/data";
            Connection con = DriverManager.getConnection(url, "root", "");
            String sql = "SELECT * FROM bill";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            updateTable(rs);
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(255, 250, 240));
        panel_3.setBounds(42, 48, 631, 725);
        panel_3.add(scrollPane, BorderLayout.CENTER);
        add(panel_3);
        panel_3.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Tìm kiếm:");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setBounds(30, 18, 90, 20);
        panel_3.add(lblNewLabel);
        
        txtFind = new JTextField();
        txtFind.setBounds(116, 14, 158, 36);
        panel_3.add(txtFind);
        txtFind.setColumns(10);
        txtFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String content = txtFind.getText();
        		String sql = "SELECT * FROM bill WHERE bill_ID LIKE ?";
        		search(content, sql);
        	}
        });
        JButton btnFind = new JButton("Tìm");
        btnFind.setBackground(new Color(255, 255, 255));
        btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnFind.setBounds(524, 14, 97, 36);
        btnFind.setBorder(new RoundedBorder(20));
        panel_3.add(btnFind);
        
        JSpinner dateSpinner = new JSpinner();
        dateSpinner.setModel(new SpinnerDateModel(new Date(1718038800000L), null, null, Calendar.DAY_OF_YEAR));
        dateSpinner.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dateSpinner.setBounds(284, 14, 230, 34);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        panel_3.add(dateSpinner);
        btnFind.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String date = sdf.format((Date) dateSpinner.getValue());
             String sql = "SELECT * FROM bill WHERE Time LIKE ?";
             search(date, sql);
    	}
    });
        
        
}
	public static void search(String search, String sql) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/data";
	        Connection con = DriverManager.getConnection(url, "root", "");
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + search + "%"); 
	        ResultSet rs = pstmt.executeQuery();
	        updateTable(rs); 
	        rs.close();
	        pstmt.close();
	        con.close();
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
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
	public static void UpdateDataToComboBox() {
		cbx.removeAllItems();
		 ArrayList<String> list = LoadDataToCombobox();
	        for(String item : list) {
	        	cbx.addItem(item.toString());  
	        }   
	}

	 public void createPDF(Object[] rowData, Object billID, String[] content, String[] name, String[] price ,String[] quantity, Object Total, String[] total_price, int finalPrice, int discountPrice) {

	        try {
	            PDDocument document = new PDDocument();
	            PDPage page = new PDPage();
	            document.addPage(page);
	            PDPageContentStream contentStream = new PDPageContentStream(document, page);
	            PDFont font = null;
	            try {
	                //font = PDType0Font.load(document, new File("/Users/macbook/eclipse-workspace/Java_Database/font/arial-unicode-ms.ttf"));
	            	font = PDType0Font.load(document, new File("src/font/arial-unicode-ms.ttf"));
	            } catch (IOException e) {
	            	JOptionPane.showMessageDialog(null, "Not found! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            float fontSize = 11;
	            float leading = 1.5f * fontSize;
	            float margin = 50;
	            float yStart = page.getMediaBox().getHeight() - margin;
	            float yPosition = yStart;
	            String separator = "-------------------------------------------------------------";
	            String[] combine = new String[content.length];
	            for(int i = 0; i < content.length; i++) {
	            	if (i < rowData.length) {
	                    combine[i] = content[i] + ": " + rowData[i];
	                } else {
	                    combine[i] = content[i] + ":";
	                }
	            }
	            float textWidth = font.getStringWidth("PHIẾU THANH TOÁN") / 1000 * 16;
	            float centerX = (page.getMediaBox().getWidth() - textWidth) / 2;
	            contentStream.beginText();
	            contentStream.setFont(font, 16);
	            contentStream.newLineAtOffset(centerX + 30, yPosition); 
	            contentStream.showText("LAVA RESTAURANT");
	            contentStream.endText();
	            yPosition -= leading * 2;
	            
	            contentStream.beginText();
	            contentStream.setFont(font, 14); 
	            contentStream.newLineAtOffset(centerX + 30, yPosition); 
	            contentStream.showText("PHIẾU THANH TOÁN");
	            contentStream.endText();
	            yPosition -= leading * 2; 
	            
	            
	            for (String data : combine) {
	            	contentStream.beginText();
	                contentStream.setFont(font, fontSize);
	                contentStream.newLineAtOffset(centerX, yPosition);
	                contentStream.showText(data);
	                contentStream.endText();
	                yPosition -= leading;
	           }
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText("Tên món                 Đơn giá    SL     Thành tiền");
	            contentStream.endText();
	            yPosition -= leading;
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText(separator);
	            contentStream.endText();
	            yPosition -= leading;
	           

	            String[] combineFood = new String[name.length];

	            for (int i = 0; i < name.length; i++) {
	                combineFood[i] = String.format("%-5s %-23s %-8s", (i + 1) + ".", name[i], price[i]) + String.format("%-2s %10s", quantity[i], total_price[i]);
	            }
	            for (String data : combineFood) {
	                contentStream.beginText();
	                contentStream.setFont(font, fontSize);
	                contentStream.newLineAtOffset(centerX, yPosition);
	                contentStream.showText(data);
	                contentStream.endText();
	                yPosition -= leading;
	            }
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText(separator);
	            contentStream.endText();
	            yPosition -= leading;
	            
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText("Tổng   :" + String.format("%55s", Total.toString()));
	            contentStream.endText();
	            yPosition -= leading;
	            
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText("Giảm   :" + String.format("%55s", "-" + discountPrice));
	            contentStream.endText();
	            yPosition -= leading;
	            
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText("T.toán :" + String.format("%55s", finalPrice));
	            contentStream.endText();
	            yPosition -= leading;
	            
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText(separator);
	            contentStream.endText();
	            yPosition -= leading;
	            
	            contentStream.beginText();
	            contentStream.setFont(font, fontSize);
	            contentStream.newLineAtOffset(centerX, yPosition);
	            contentStream.showText("        Xin cảm ơn và hẹn gặp lại quý khách!");
	            contentStream.endText();			
	            yPosition -= leading;
	            contentStream.close();
	            document.save(billID.toString()+".pdf");
	            document.close();
	            JOptionPane.showMessageDialog(null, "Xác nhận và in hoá đơn thành công.");
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Không thể xuất hoá đơn: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	 public static void updateTable(ResultSet rs) {
		    try {
		        ResultSetMetaData metaData = rs.getMetaData();
		        int columnCount = metaData.getColumnCount();
		        model.setRowCount(0); 
		        model.setColumnCount(0);
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            model.addColumn(metaData.getColumnLabel(columnIndex));
		        }
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            if (columnIndex == 2) {
		                table.getColumnModel().getColumn(columnIndex - 1).setPreferredWidth(180);
		            } else {
		                table.getColumnModel().getColumn(columnIndex - 1).setPreferredWidth(80);
		            }
		        }
		        while (rs.next()) {
		            Object[] row = new Object[columnCount];
		            for (int i = 1; i <= columnCount; i++) {
		                row[i - 1] = rs.getObject(i);
		            }
		            model.addRow(row);
		        }
		        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		        table.revalidate();
		        table.repaint();
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

	    public void editBill(Object[] rowData, Object billID) {
	    	
	    	panel.removeAll();
	        JTextField[] textFields = new JTextField[7];
	        JTextArea textArea = new JTextArea();
	        String[] columnNames = {"Mã bill", "Thời gian", "Mã bàn", "NV tạo đơn", "NV thanh toán", "Tổng tiền",  "Trạng thái", "Món"};
	        for (int i = 0; i < columnNames.length; i++) {
	            JLabel label = new JLabel(columnNames[i] + ":");
	            label.setBounds(150, 15 + 25 * i, 100, 20);
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
	            	textArea.setBounds(340,15 + 25*i, 200, 100);
	            	textArea.setBackground(Color.white);
	            	textArea.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setBounds(340, 15 + 25 * i, 200, 100);
					scrollPane.setEnabled(false);
					scrollPane.setPreferredSize(new Dimension(200, 150));
					panel.add(scrollPane, BorderLayout.CENTER);
	            } else {
	            	textFields[i] = new JTextField();
	            	textFields[i].setBounds(340, 15 + 25 * i, 200, 20);
	            	textFields[i].setEditable(false);
	            	if(i < rowData.length) {
	            		textFields[i].setText(rowData[i].toString());
	            }
	            panel.add(textFields[i]);
	            }
	        }
	        JTextField discount = new JTextField();
	        JLabel label1 = new JLabel("Giảm giá(%):");
	        label1.setBounds(150, 300, 100, 20);
	        discount.setBounds(340,300,200,20);
	        discount.setText("0");
	        
	        panel.add(label1);
	        panel.add(discount);
	        
	        btnUpdate = new JButton("Xác nhận và in hoá đơn");
	        btnUpdate.setHorizontalAlignment(SwingConstants.CENTER);
	        btnUpdate.setBorder(new RoundedBorder(20));
	        btnUpdate.setBackground(new Color(255, 255, 255));
	        btnUpdate.setBounds(355, 330, 180, 30);
	        panel.add(btnUpdate);       
	        btnUpdate.setEnabled(true);
	        btnUpdate.addActionListener(new ActionListener() {
	            @Override
public void actionPerformed(ActionEvent e) {
	            	
	            	try { 
	            	int dc = Integer.parseInt(discount.getText());
	            	int totalBill = Integer.parseInt(textFields[5].getText());

	                if (dc < 0 || dc > 100) {
	                   JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ. Hãy nhập lại.");
	                   discount.setText("0");
	                } else { 
	                    try {
	                    dc = Integer.parseInt(discount.getText());
	                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
	                    String sqlBill = "UPDATE bill SET Status = ?, Total = ? WHERE Bill_ID=?";
	                    PreparedStatement pstmtBill = con.prepareStatement(sqlBill);
	                    pstmtBill.setBoolean(1, true);
	                    discountPrice = totalBill * dc / 100;
	                    finalPrice = totalBill - discountPrice;
	                
						pstmtBill.setInt(2, finalPrice);
						
						pstmtBill.setInt(3, Integer.parseInt(textFields[0].getText())); 
						SelectedValue = Integer.parseInt(textFields[2].getText());
	                    int rowsAffectedBill = pstmtBill.executeUpdate();
	                    pstmtBill.close();
	                   
	                    if (rowsAffectedBill > 0) {
	                        
	                        status = 1;
	                        connect connector = new connect();
	        				Connection conn = connector.connection;
	        				Statement stmt = null;
	        				String updateTableQuery = "UPDATE tables SET Status = 0 WHERE Table_ID = " + SelectedValue; 
	        				btnUpdate.setEnabled(false);
	  		        	  try {
	  		        		  stmt = conn.createStatement();
	  		        		  stmt.executeUpdate(updateTableQuery);
	  		        		  Goimon.updateTableStatusInUI();
	  		        		  conn.close();
	  		        		  stmt.close();
	  		        	  } catch (SQLException e1) {
	  						e1.printStackTrace();
	  		        	  }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Không thể xác nhận thanh toán.");
	                    }
	                    con.close();
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                
	                String[] content = { "Mã bill", "Thời gian", "Bàn", "NV tạo đơn", "NV thu tiền" };
					String[] name = null;
					String[] quantity = null;
					String[] price = null;
					String[] total_price = null;
					try {
						String selectRow = null;
						Class.forName("com.mysql.jdbc.Driver");
						String url = "jdbc:mysql://localhost:3306/data";
						Connection con = DriverManager.getConnection(url, "root", "");
						String sql = "SELECT * FROM bill Where bill_ID = ?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, curr);
						ResultSet rs = pstmt.executeQuery();
						Object billID = null;
						Object Total = null;
						Object[] rowData = null;
						 
						if (rs.next()) {
							selectRow = rs.getString("bill_ID");
							rowData = new Object[model.getColumnCount()];
							for (int i = 0; i < model.getColumnCount(); i++) {
								rowData[i] = rs.getObject(i + 1);
							}
							billID = rowData[0];
							Total = totalBill;
							rowData[3] = getName((int)rowData[3]);
							rowData[4] = getName((int) rowData[4]);
							Component[] components = getComponents();
							for (Component component : components) {
								if (component instanceof JTextField) {
									remove(component);
								}
							}
						}
						String query = "SELECT fdc.Name, fdc.Price, od.Quantity, od.total_price "
								+ "FROM food_drink AS fdc " + "JOIN order_details AS od ON fdc.ID = od.item_ID "
								+ "JOIN bill AS b ON od.bill_ID = b.bill_ID " + "WHERE b.bill_ID = ?";
						PreparedStatement pstmt1 = con.prepareStatement(query);
						pstmt1.setString(1, billID.toString());
						ResultSet rs1 = pstmt1.executeQuery();
						rs1.last(); 
						int numRows = rs1.getRow(); 
						rs1.beforeFirst();
						name = new String[numRows];
						quantity = new String[numRows];
						price = new String[numRows];
						total_price = new String[numRows];
						int index = 0;
						while (rs1.next()) {
							name[index] = rs1.getString("Name");
							quantity[index] = rs1.getString("Quantity");
							price[index] = rs1.getString("Price");
							total_price[index] = rs1.getString("total_price");
							index++;
						}
							createPDF(rowData, billID, content, name, price, quantity, Total, total_price, finalPrice,
								discountPrice);
						rs.close();
						rs1.close();
						pstmt.close();
						pstmt1.close();
						con.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Loi: " + ex.getMessage(), "Loi:",
								JOptionPane.ERROR_MESSAGE);
					}
					UpdateDataToComboBox();
	            }
	            } catch (NumberFormatException er) {
	            	JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ. Hãy nhập lại.");
	            	discount.setText("");
	            }
	            }	        });
	        revalidate();
	        repaint();
	    }
	    
	    public String getName(int rowData) {
	        String name = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/data";
	            Connection con = DriverManager.getConnection(url, "root", "");
	            String sql = "SELECT Name FROM employment WHERE Emp_ID = ?";
	            PreparedStatement pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, rowData);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                name = rs.getString("Name");
	            }

	            rs.close();
	            pstmt.close();
	            con.close();
	        } catch (Exception er) {
	            er.printStackTrace();
	        }

	        return name;
	    }
	    public void actionPerformed(ActionEvent e) {
	    	String select = "";
	    	if(cbx.getSelectedIndex() != -1) {
	    		select = cbx.getSelectedItem().toString();
			}
	    	if(select != "") {
	    		SelectedValue = Integer.parseInt(select);
	    	} // else return;
			empCash = Integer.parseInt(dangnhap1.getID());
			if (e.getSource() == edit) {			
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
					String sqlBill = "UPDATE bill SET Emp_Cash = ? WHERE Table_ID = ?";
					PreparedStatement pstmtBill = con.prepareStatement(sqlBill);
					pstmtBill.setInt(1, empCash);
					pstmtBill.setInt(2, SelectedValue);
					int rowsAffectedBill = pstmtBill.executeUpdate();
					pstmtBill.close();
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				try {
					String selectRow = null;
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/data";
					Connection con = DriverManager.getConnection(url, "root", "");
					String sql = "SELECT * FROM bill Where Table_ID = ? and Status = 0";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, SelectedValue);	
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						selectRow = rs.getString("bill_ID");
						Object[] rowData = new Object[model.getColumnCount()];
						for (int i = 0; i < model.getColumnCount(); i++) {
							rowData[i] = rs.getObject(i + 1);
						}
						Object billID = rowData[0];
						curr = (int) billID;
						Component[] components = getComponents();
						for (Component component : components) {
							if (component instanceof JTextField) {
								remove(component);
							}
						}
						editBill(rowData, billID);
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Lỗi",
			                JOptionPane.ERROR_MESSAGE);
				}
			}  
	    	
	}
}