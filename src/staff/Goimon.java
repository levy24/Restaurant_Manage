package staff;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Messages;

import connectDTB.connect;
import data_cache.Bill_Cache;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;
import login.dangnhap1;
import staff.Bill;
import javax.swing.JTextField;

public class Goimon extends JPanel {

	private static final long serialVersionUID = 1L;

	public int kt;
	public boolean ktbtn[] = new boolean[25];
	public static Button button[] = new Button[25];
	private JComboBox cbName; 
	private int id, price;
	private long total = 0;
	JTextPane tpTotal;
	private JTextField txtFind;
	private DefaultTableModel model;
	static Color gr = new Color(0,255,128);
	static Color re = new Color(220,20,60);
	static Color ye = new Color(255, 255, 128);
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Goimon() {
		setBackground(new Color(255, 222, 173));
		int n = 25;
		setBounds(0, 0, 1540, 815);
		
		
		setForeground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		for (int i=0;i<n;i++)
			ktbtn[i] = true;
		
		JLabel lblNewLabel = new JLabel("ORDER");
		lblNewLabel.setForeground(new Color(75, 0, 130));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1550, 50);
		add(lblNewLabel);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 250, 240));
		panel2.setBounds(700, 100, 825, 650);	
		add(panel2);
		panel2.setLayout(null);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JComboBox cbClassify = new JComboBox();
		cbClassify.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cbClassify.setBorder(BorderFactory.createLineBorder(Color.black));
		cbClassify.setModel(new DefaultComboBoxModel(new String[] {"Khai vị", "Món chính", "Tráng miệng", "Đồ uống"}));
		cbClassify.setBounds(200, 70, 150, 21);
		panel2.add(cbClassify);

		cbName = new JComboBox();
		cbName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cbName.setBorder(BorderFactory.createLineBorder(Color.black));
		populateComboBox(1);
		
		JLabel classify = new JLabel("Loại:");
		classify.setForeground(new Color(75, 0, 130));
		classify.setFont(new Font("Times New Roman", Font.BOLD, 18));
		classify.setBounds(100, 70, 45, 20);
		panel2.add(classify);
		
		JLabel Name = new JLabel("Chọn món:");
		Name.setForeground(new Color(75, 0, 130));
		Name.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Name.setBounds(100, 165, 90, 20);
		panel2.add(Name);
		
		JLabel Quantity = new JLabel("Số lượng:");
		Quantity.setForeground(new Color(75, 0, 130));
		Quantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Quantity.setBounds(100, 210, 90, 20);
		panel2.add(Quantity);
		
		JLabel Table = new JLabel("Số bàn:");
		Table.setForeground(new Color(75, 0, 130));
		Table.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Table.setBounds(100, 250, 70, 20);
		panel2.add(Table);
		
		//Đổ dữ liệu lên chọn món khi có loại 
		cbClassify.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        String selectedType = (String)cb.getSelectedItem();
		        
		        cbName.removeAllItems();
		        int classify = 0;
		        if (selectedType.equals("Khai vị"))
		        	classify = 1;
		        else if (selectedType.equals("Món chính")) 
		        	classify = 2;
		        else if (selectedType.equals("Tráng miệng")) 
		                classify = 3;
		        else if(selectedType.equals("Đồ uống"))
		        	classify = 4;
		        populateComboBox(classify);		        
		    }
		});
		cbName.setBounds(200, 165, 150, 21);
		panel2.add(cbName);
		
		JLabel Find = new JLabel("Tìm kiếm:");
		Find.setForeground(new Color(75, 0, 130));
		Find.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Find.setBounds(100, 115, 90, 20);
		panel2.add(Find);
		
		txtFind = new JTextField();
		txtFind.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtFind.setBorder(BorderFactory.createLineBorder(Color.black));
		txtFind.setBounds(200, 118, 150, 21);
		panel2.add(txtFind);
		
		JButton btnSearch = new JButton("Tìm");
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSearch.setBorder(BorderFactory.createLineBorder(Color.black));
		btnSearch.setForeground(new Color(75, 0, 130));
		btnSearch.setBounds(370, 117, 85, 21);
		btnSearch.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	cbName.removeAllItems();
		        connect connector = new connect();
		        try (Connection conn = connector.connection;
		             Statement stmt = conn.createStatement();
		             ResultSet resultSet = stmt.executeQuery("SELECT Name FROM food_drink WHERE Name LIKE '%" + txtFind.getText() + "%' AND Status = 1")) {
		            while (resultSet.next()) {
		                cbName.addItem(resultSet.getString("Name"));
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		panel2.add(btnSearch);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		spinner.setBorder(BorderFactory.createLineBorder(Color.black));
		spinner.setBounds(200, 210, 50, 25);
		panel2.add(spinner);
		
		JTextPane tpTable = new JTextPane();
		tpTable.setBackground(new Color(255, 255, 255));
		tpTable.setBorder(BorderFactory.createLineBorder(Color.black));
		tpTable.setEditable(false);
		tpTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tpTable.setBounds(200, 250, 50, 25);
		panel2.add(tpTable);
		

		JTable table = new JTable();

		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Tên món");
		model.addColumn("Giá (VND)");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		table.setModel(model);
		
		// Lấy đối tượng của cột ID từ model của bảng
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn idColumn = columnModel.getColumn(0);

		// Ẩn cột ID
		columnModel.removeColumn(idColumn);

        
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 320, 800, 250);
		panel2.add(scrollPane);
		
		JButton btnDelete = new JButton("Xóa món");
		btnDelete.setForeground(new Color(75, 0, 130));
		btnDelete.setBorder(BorderFactory.createLineBorder(Color.black));
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnDelete.setBounds(570, 115, 150, 40);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int selectedRowIndex = table.getSelectedRow();
		        if (selectedRowIndex != -1) {
		            
		            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa món này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
		            
		            if (choice == JOptionPane.YES_OPTION) {
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                int quantity = (int) table.getValueAt(selectedRowIndex, 3);
		                int price = (int) table.getValueAt(selectedRowIndex, 2); 
		                total -= price * quantity; 
		                model.removeRow(selectedRowIndex);
		                tpTotal.setText(String.valueOf(total));
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một món để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		panel2.add(btnDelete);
		
		JButton btnChoose = new JButton("Chọn món");
		btnChoose.setForeground(new Color(75, 0, 130));
		btnChoose.setBorder(BorderFactory.createLineBorder(Color.black));
		btnChoose.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnChoose.setBounds(570, 180, 150, 40);
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				 String selectedItem = (String) cbName.getSelectedItem(); 
			     int quantity = (int) spinner.getValue(); 

			     id = -1;
			     price = -1;
			     connect connector = new connect();
			     try (Connection conn = connector.connection;
			          Statement stmt = conn.createStatement();
			          ResultSet resultSet = stmt.executeQuery("SELECT ID, Price FROM food_drink WHERE Name = '" + selectedItem + "'")) {
			         if (resultSet.next()) {
			             id = resultSet.getInt("ID");
			             price = resultSet.getInt("Price");
			         }
			     } catch (SQLException e1) {
			         e1.printStackTrace();
			     }
			     
			     //kiểm tra món ăn đã được chọn trước đó hay chưa
			     boolean itemExists = false;
			     for (int i = 0; i < model.getRowCount(); i++) {
			         String existingItem = (String) model.getValueAt(i, 1);
			         if (existingItem.equals(selectedItem)) {
			             itemExists = true;
			             break;
			         }
			     }
			     if (itemExists) {
			         JOptionPane.showMessageDialog(null, "Món ăn đã được chọn trước đó. Vui lòng chọn một món khác.", "Thông báo", JOptionPane.WARNING_MESSAGE);

			     } else {
			         Object[] row = new Object[5];
			         row[0] = id; 
			         row[1] = selectedItem;
			         row[2] = price; 
			         row[3] = quantity;
			         row[4] = quantity*price;
			         model.addRow(row);
			         total += price * quantity;
			     }
			     tpTotal.setText(String.valueOf(total));
			}
		});
		panel2.add(btnChoose);
		
		JButton btnUpdate = new JButton("Sửa");
		btnUpdate.setForeground(new Color(75, 0, 130));
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnUpdate.setBorder(BorderFactory.createLineBorder(Color.black));
		btnUpdate.setBounds(570, 50, 150, 40);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex != -1) {
				    String selectedFoodName = (String) table.getValueAt(selectedRowIndex, 0);
				    String input = JOptionPane.showInputDialog(null, "Nhập số lượng mới cho món " + selectedFoodName + ":", "Sửa số lượng", JOptionPane.PLAIN_MESSAGE);
				    if (input != null && !input.isEmpty()) {
				        try {
				            int oldQuantity = (int) model.getValueAt(selectedRowIndex, 3);
				            int price = (int) model.getValueAt(selectedRowIndex, 2);
				            int newQuantity = Integer.parseInt(input);
				            total -= price * oldQuantity;
				            total += price * newQuantity;
				            model.setValueAt(newQuantity, selectedRowIndex, 3);
				            model.setValueAt(newQuantity*price, selectedRowIndex, 4);
				            tpTotal.setText(String.valueOf(total));
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				} else {
				    JOptionPane.showMessageDialog(null, "Vui lòng chọn một món ăn để có thể sửa số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel2.add(btnUpdate);
	
		JButton btnConfirm = new JButton("Chốt đơn");
		btnConfirm.setForeground(new Color(75, 0, 130));
		btnConfirm.setBorder(BorderFactory.createLineBorder(Color.black));
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnConfirm.setBounds(570, 250, 150, 40);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (model.getRowCount() == 0) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn món ăn nào.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
				String tptableString = "";
				
				int tableID = -1;
				
				if(tpTable.getText() == "" ) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi gọi món!", "Lời nhắc", JOptionPane.WARNING_MESSAGE);
				} else {
					tptableString = tpTable.getText();
					tableID =  Integer.parseInt(tptableString);
				}
				if(tableID == -1) {
					return;
				}
		        String time = getTimeFromSystem(); 
		        int empOrder =  Integer.parseInt(dangnhap1.loggedInUserID);
		        long totalBill = total;
		        Bill_Cache billCache = new Bill_Cache();
		        int currentBillID = getCurrentBillID(); 
		        
		        int exitingBillID = billCache.getOpenBillIDForTable(tableID);
		        
		        //kiểm tra bàn đã có khách ngồi và muốn đặt thêm món 
		        if(exitingBillID != -1)
		        {
		        	for (int i = 0; i < model.getRowCount(); i++) {
			            int itemID = (int) model.getValueAt(i, 0);
			            int quantity = (int) model.getValueAt(i, 3);
			            int price = (int) model.getValueAt(i, 2)*quantity;
			            
			            boolean itemExistsInBill = billCache.hasItemInBill(exitingBillID, itemID);
			            if(itemExistsInBill)
			            {
			            	boolean success = billCache.updateOrderDetailQuantity(exitingBillID, itemID, quantity, price);
			            	if(!success)
			            	{
			            		JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lương món trong hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            		return;
			            	}
			            	
			            	boolean success1 = billCache.updateBillTotal(exitingBillID);
			            	if(!success1)
			            	{
			            		JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số tiền trong hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            		return;
			            	}
			            }
			            else {
							boolean success = billCache.addOrderDetail(exitingBillID, itemID, quantity, price);
							if(!success)
			            	{
			            		JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết đơn hàng vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            		return;
			            	}
							
							boolean success1 = billCache.updateBillTotal(exitingBillID);
			            	if(!success1)
			            	{
			            		JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số tiền trong hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            		return;
			            	}
						}
		        	}
		        }

		        else {
		        	boolean success = billCache.addBill(currentBillID, time, tableID, empOrder, totalBill, 0);
		        	if(!success)
		        	{
		        		JOptionPane.showMessageDialog(null, "Lỗi khi thêm hóa đơn vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        		return;
		        	}
		        	
		        	for (int i =0; i<model.getRowCount(); i++)
		        	{
		        		int itemID = (int) model.getValueAt(i, 0);
		        		int quantity = (int) model.getValueAt(i, 3);
		        		int price = (int) model.getValueAt(i, 2) *quantity;
		        		boolean success1 = billCache.addOrderDetail(currentBillID, itemID, quantity, price);
		        		if(!success1)
		        		{
		        			JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        			return;
		        		}
		        	}
		        }
		        
		        if (exitingBillID == -1) {
		        	  String updateTableQuery = "UPDATE tables SET Status = 1 WHERE Table_ID = " + tableID; 
		        	  try {
		        		  connect connector = new connect();
		                  Connection conn = connector.connection;
		                  Statement stmt = conn.createStatement();
		                  stmt.executeUpdate(updateTableQuery);
		                  stmt.close();
		                  conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		      
		        JOptionPane.showMessageDialog(null, "Hóa đơn đã được chốt thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        model.setRowCount(0);
		        total = 0; 
		        tpTotal.setText("0");
		    
				int k = Integer.parseInt(tpTable.getText());
				ktbtn[k-1]=false;
				button[k-1].setBackground(re);
				
				tpTable.setText("");
//				Bill bill = new Bill();
//				
				Bill.UpdateDataToComboBox();			}
		
		});
		panel2.add(btnConfirm);
		
		JLabel Tien = new JLabel("Tổng tiền:");
		Tien.setForeground(new Color(75, 0, 130));
		Tien.setFont(new Font("Times New Roman", Font.BOLD, 22));
		Tien.setHorizontalAlignment(SwingConstants.CENTER);
		Tien.setBounds(220, 600, 100, 25);
		panel2.add(Tien);
		
		tpTotal = new JTextPane();
		tpTotal.setForeground(new Color(75, 0, 130));
		tpTotal.setEditable(false);
		tpTotal.setBorder(BorderFactory.createLineBorder(Color.black));
		tpTotal.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		tpTotal.setBounds(350, 600, 150, 25);
		panel2.add(tpTotal);
		
		JLabel lblNewLabel_2 = new JLabel("GỌI MÓN");
		lblNewLabel_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(1040, 55, 128, 40);
		add(lblNewLabel_2);
		
		//Panel chọn bàn
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(10, 100, 650, 650);
		add(panel);
		panel.setLayout(new GridLayout(5, 5, 10, 10));
		
		JLabel lblNewLabel_1 = new JLabel("BÀN ĂN");
		lblNewLabel_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(273, 58, 128, 40);
		add(lblNewLabel_1);
		
		String btn[] = {"1","2","3","4","5",
				"6","7","8","9","10",
				"11","12","13","14","15",
				"16","17","18","19","20",
				"21","22","23","24","25"};
		connect connector = new connect();
		Connection conn = connector.connection;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Table_ID, Status FROM tables");
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			int i = 0;

			while (rs.next() && i < n) {
			    int tableId = rs.getInt("Table_ID");
			    int tableStatus = rs.getInt("Status");
			    button[i] = new Button(btn[i]);
			    button[i].setFont(new Font("Times New Roman", Font.BOLD, 25));
			    button[i].setBackground(gr);
			    panel.add(button[i]);
			    if(tableStatus == 1) 
			        button[i].setBackground(re); 
			    final int currentIndex = i;
			    button[i].addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            if (button[currentIndex].getBackground() == gr) {
			                JFrame frame = new JFrame();
			                frame.setTitle("Status");
			                frame.setBounds(550,300,400,200);
			                frame.getContentPane().setLayout(new GridLayout(2,1,10,10));
			                JLabel label = new JLabel("Xác nhận chọn bàn này?");
			                label.setFont(new Font("Times New Roman", Font.BOLD, 25));
			                label.setHorizontalAlignment(SwingConstants.CENTER);
			                frame.getContentPane().add(label);
			                JPanel panel = new JPanel();
			                panel.setLayout(new FlowLayout());
			                JButton btn1 = new JButton("Hủy");
			                JButton btn2 = new JButton("Xác Nhận");
			                btn1.addActionListener(new ActionListener() {
			                    public void actionPerformed(ActionEvent h) {
			                        frame.setVisible(false);
			                    }
			                });
			                btn2.addActionListener(new ActionListener() {
			                    public void actionPerformed(ActionEvent h) {
			                        tpTable.setText(String.valueOf(currentIndex + 1));
			                        button[currentIndex].setBackground(ye);
			                        for (int j=0; j<n; j++) {
			                            if (button[j].getBackground() == ye && j != currentIndex) {
			                                button[j].setBackground(gr);
			                                break;
			                            }
			                        }
			                        frame.setVisible(false);
			                    }
			                });
			                panel.add(btn1);
			                panel.add(btn2);
			                frame.getContentPane().add(panel);
			                frame.setVisible(true);
			            } else if (button[currentIndex].getBackground() == re) {
			            	boolean kt = true;
			            	for (int j=0;j<n;j++)
			            		if (button[j].getBackground() == ye)
			            		{
			            			JOptionPane.showMessageDialog(null, "Bàn này đang có khách!!!");
			            			kt = false;
			            		}
			                if (kt) {
			                	total = 0; 
			    		        tpTotal.setText("0");
			                	model.setRowCount(0);
			                	tpTable.setText("");
			                	try {
			                		tpTable.setText(String.valueOf(currentIndex + 1));
			                		connect connector = new connect();
			                	    Connection conn = connector.connection;
			                	    String sql = "SELECT bill_ID FROM bill WHERE Table_ID = ? AND Status = 0";
			                	    PreparedStatement pstmt = conn.prepareStatement(sql);
			                	    pstmt.setInt(1, currentIndex + 1);
			                	    ResultSet resultSet = pstmt.executeQuery();

			                	    // Duyệt qua kết quả truy vấn và lấy thông tin từng món ăn
			                	    while (resultSet.next()) {
			                	        int billID = resultSet.getInt("bill_ID");			                	
			                	        String sqlItems = "SELECT Item_ID, quantity FROM order_details WHERE bill_id = ?";
			                	        PreparedStatement pstmtItems = conn.prepareStatement(sqlItems);
			                	        pstmtItems.setInt(1, billID);
			                	        ResultSet resultSetItems = pstmtItems.executeQuery();

			                	        // Duyệt qua từng mục trong chi tiết đơn hàng
			                	        while (resultSetItems.next()) {
			                	            int itemID = resultSetItems.getInt("Item_ID");
			                	            int quantity = resultSetItems.getInt("quantity");

			                	            // Thực hiện truy vấn để lấy thông tin món ăn từ cơ sở dữ liệu
			                	            String sqlFoodDrink = "SELECT Name, price FROM food_drink WHERE ID = ?";
			                	            PreparedStatement pstmtFoodDrink = conn.prepareStatement(sqlFoodDrink);
			                	            pstmtFoodDrink.setInt(1, itemID);
			                	            ResultSet resultSetFoodDrink = pstmtFoodDrink.executeQuery();

			                	            // Nếu có kết quả, thêm thông tin vào bảng hoặc mô hình bảng
			                	            if (resultSetFoodDrink.next()) {
			                	                String selectedItem = resultSetFoodDrink.getString("Name");
			                	                int price = resultSetFoodDrink.getInt("price");

			                	                // Đưa dữ liệu vào bảng hoặc mô hình bảng tại đây
			                	                Object[] row = new Object[5];
			                	                row[0] = itemID;
			                	                row[1] = selectedItem;
			                	                row[2] = price;
			                	                row[3] = quantity;
			                	                row[4] = quantity * price;
			                	                model.addRow(row);
			                	                total += price * quantity;
			                	                tpTotal.setText(String.valueOf(total));
			                	            }
			                	        }
			                	    }
			                	    
			            	        rs.close();
			            	        pstmt.close();
			            	        conn.close();
			            	    } catch (Exception ex) {
			            	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            	    }
			                }
			            }
			        }
			    });
			    i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panel2.add(btnConfirm);
				
	}
	public int getCurrentBillID() {
	    int currentBillID = 0;
	    try {
	    	connect connector = new connect();
			Connection conn = connector.connection;
	        String sql = "SELECT MAX(bill_ID) FROM bill";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            currentBillID = rs.getInt(1);
	            if (!rs.wasNull()) {
	                currentBillID++; 
	            }
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    return currentBillID;
	}
	public static String getTimeFromSystem() {
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }
	
	private void populateComboBox(int classify) {
	    connect connector = new connect();
	    try (Connection conn = connector.connection;
	    	     PreparedStatement pstmt = conn.prepareStatement("SELECT Name FROM food_drink WHERE classify = ? AND Status = 1")) {
	    	    pstmt.setInt(1, classify);
	    	    try (ResultSet resultSet = pstmt.executeQuery()) {
	    	        while (resultSet.next()) {
	    	            cbName.addItem(resultSet.getString("Name"));
	    	        }
	    	    }
	    	} catch (SQLException e) {
	    	    e.printStackTrace();
	    	}
	}
	
	public static void updateTableStatusInUI() {
	    try {
	    	connect connector = new connect();
			Connection conn = connector.connection;
	        String sql = "SELECT Table_ID, Status FROM tables";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int tableId = rs.getInt("Table_ID");
	            int tableStatus = rs.getInt("Status");
	            if (tableStatus == 1) {
	                button[tableId - 1].setBackground(re);
	            } else {
	                button[tableId - 1].setBackground(gr);
	            }
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}