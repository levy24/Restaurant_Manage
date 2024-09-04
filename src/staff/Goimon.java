package staff;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.border.Border;
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
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Goimon extends JPanel {

	private static final long serialVersionUID = 1L;

	public int kt;
	public boolean ktbtn[] = new boolean[25];
	public static Button button[] = new Button[25];
	private JComboBox cbName; 
	private int id, price;
	private String donVi;
	private long total = 0;
	JTextPane tpTotal;
	private JTextField txtFind;
	private DefaultTableModel model;
	static Color gr = new Color(0,255,128);
	static Color re = new Color(220,20,60);
	static Color ye = new Color(255, 255, 128);
	private int billID = -1;
	private JTextField  txtGia = new JTextField("0");
	private JTextField txtDonVi = new JTextField();
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
		

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 250, 240));
		panel2.setBounds(699, 100, 810, 650);	
		add(panel2);
		panel2.setLayout(null);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JComboBox cbClassify = new JComboBox();
		cbClassify.setForeground(new Color(75, 0, 130));
		cbClassify.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cbClassify.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		cbClassify.setModel(new DefaultComboBoxModel(new String[] {"Khai vị", "Món chính", "Tráng miệng", "Đồ uống"}));
		cbClassify.setBounds(212, 85, 198, 25);
		panel2.add(cbClassify);

		cbName = new JComboBox();
		cbName.setForeground(new Color(75, 0, 130));
		cbName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cbName.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		populateComboBox(1);
		
		JLabel classify = new JLabel("Loại:");
		classify.setForeground(new Color(75, 0, 130));
		classify.setFont(new Font("Times New Roman", Font.BOLD, 18));
		classify.setBounds(78, 87, 45, 20);
		panel2.add(classify);
		
		JLabel Name = new JLabel("Chọn món:");
		Name.setForeground(new Color(75, 0, 130));
		Name.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Name.setBounds(77, 134, 90, 20);
		panel2.add(Name);
		
		JLabel Quantity = new JLabel("Số lượng:");
		Quantity.setForeground(new Color(75, 0, 130));
		Quantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Quantity.setBounds(77, 233, 90, 20);
		panel2.add(Quantity);
		
		JLabel Table = new JLabel("Số bàn:");
		Table.setForeground(new Color(75, 0, 130));
		Table.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Table.setBounds(77, 275, 73, 20);
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
		cbName.setBounds(212, 132, 198, 25);
		panel2.add(cbName);
		
		JLabel Find = new JLabel("Tìm kiếm:");
		Find.setForeground(new Color(75, 0, 130));
		Find.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Find.setBounds(78, 38, 90, 20);
		panel2.add(Find);
		
		txtFind = new JTextField();
		txtFind.setForeground(new Color(75, 0, 130));
		txtFind.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtFind.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		txtFind.setBounds(212, 35, 198, 25);
		panel2.add(txtFind);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(Goimon.class.getResource("/image/Ampeross-Qetto-2-Search.32.png")));
		btnSearch.setBackground(new Color(224, 255, 255));
	//	btnSearch.setIcon(new ImageIcon(Goimon.class.getResource("/image/Ampeross-Qetto-2-Search.32.png")));
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSearch.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnSearch.setForeground(new Color(75, 0, 130));
		btnSearch.setBounds(415, 38, 35, 21);
		btnSearch.setBorder(new RoundedBorder(20));
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
		
		cbName.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String selectedItem = (String) cbName.getSelectedItem();
		        connect connector = new connect();
			     try (Connection conn = connector.connection;
			          Statement stmt = conn.createStatement();
			          ResultSet resultSet = stmt.executeQuery("SELECT ID, Price, DonVi FROM food_drink WHERE Name = '" + selectedItem + "'")) {
			         if (resultSet.next()) {
			             id = resultSet.getInt("ID");
			             price = resultSet.getInt("Price");
			             txtGia.setText(String.valueOf(price));
			             donVi = resultSet.getString("DonVi");
			             txtDonVi.setText(String.valueOf(donVi));
			         }
			     } catch (SQLException e1) {
			         e1.printStackTrace();
			     }
		    }
		});

		
		JSpinner spinner = new JSpinner();
		spinner.setForeground(new Color(75, 0, 130));
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		spinner.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		spinner.setBounds(212, 232, 50, 25);
		panel2.add(spinner);
		
		JTextPane tpTable = new JTextPane();
		tpTable.setForeground(new Color(75, 0, 130));
		tpTable.setBackground(new Color(255, 255, 255));
		tpTable.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		tpTable.setEditable(false);
		tpTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tpTable.setBounds(212, 275, 50, 25);
		panel2.add(tpTable);
		
		

		JTable table = new JTable();

		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Tên món");
		model.addColumn("Giá (VND)");
		model.addColumn("Số lượng");
		model.addColumn("Đơn vị tính");
		model.addColumn("Thành tiền");
		table.setModel(model);
		
		// Lấy đối tượng của cột ID từ model của bảng
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn idColumn = columnModel.getColumn(0);

		// Ẩn cột ID
		columnModel.removeColumn(idColumn);

        
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 320, 777, 250);
		panel2.add(scrollPane);
		
		JButton btnDelete = new JButton("Xóa món");
		btnDelete.setBackground(new Color(224, 255, 255));
		btnDelete.setForeground(new Color(75, 0, 130));
		btnDelete.setBorder(null);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnDelete.setBounds(570, 99, 150, 40);
		btnDelete.setBorder(new RoundedBorder(20));
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRowIndex = table.getSelectedRow();
		        if (selectedRowIndex != -1) {
		        	int foodIDColumnIndex = 0; 
		            int foodID = (int) table.getModel().getValueAt(selectedRowIndex, foodIDColumnIndex);
		            try {
		                connect connector = new connect();
		                Connection conn = connector.connection;
		                String selectQuery = "SELECT * FROM order_details WHERE Item_ID = ? AND bill_ID = ?";
		                PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
		                selectStmt.setInt(1, foodID);
		                selectStmt.setInt(2, billID); 
		                ResultSet resultSet = selectStmt.executeQuery();

		                if (resultSet.next()) {
		                    JOptionPane.showMessageDialog(null, "Món ăn này đã được đặt hàng, không thể xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		                } else {
		                    int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa món này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
		                    if (choice == JOptionPane.YES_OPTION) {
		                        DefaultTableModel model = (DefaultTableModel) table.getModel();
		                        //JOptionPane.showMessageDialog(null, table.getValueAt(selectedRowIndex, 3), "Thông báo", JOptionPane.WARNING_MESSAGE);
		                        int quantity = (int) table.getValueAt(selectedRowIndex, 2);
		                        int price = (int) table.getValueAt(selectedRowIndex, 1);
		                        total -= price * quantity;
		                        model.removeRow(selectedRowIndex);
		                        tpTotal.setText(String.valueOf(total));
		                    }
		                }
		                conn.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Xảy ra lỗi khi kiểm tra món ăn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một món để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		panel2.add(btnDelete);
		
		JButton btnChoose = new JButton("Chọn món");
		btnChoose.setBackground(new Color(224, 255, 255));
		btnChoose.setForeground(new Color(75, 0, 130));
		btnChoose.setBorder(null);
		btnChoose.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnChoose.setBounds(570, 160, 150, 40);
		btnChoose.setBorder(new RoundedBorder(20));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				 String selectedItem = (String) cbName.getSelectedItem(); 
			     int quantity = (int) spinner.getValue(); 
			     price = Integer.parseInt(txtGia.getText());
			     donVi = txtDonVi.getText();
			     id = 1;
			     int i = 0;
			     
			     //kiểm tra món ăn đã được chọn trước đó hay chưa
			     boolean itemExists = false;
			     for (i = 0; i < model.getRowCount(); i++) {
			         String existingItem = (String) model.getValueAt(i, 1);
			         if (existingItem.equals(selectedItem)) {
			             itemExists = true;
			             break;
			         }
			     }
			     if (itemExists) {
			    	    boolean isValidQuantity = false;
			    	    while (!isValidQuantity) {
			    	        String input = JOptionPane.showInputDialog(null, selectedItem + " đã được chọn với số lượng " + (int) model.getValueAt(i, 3) + "!\nBạn muốn thay đổi số lượng thành: ", "Sửa số lượng", JOptionPane.QUESTION_MESSAGE);
			    	        if (input != null && !input.isEmpty()) {
			    	            try {
			    	                int oldQuantity = (int) model.getValueAt(i, 3);
			    	                int price = (int) model.getValueAt(i, 2);
			    	                int newQuantity = Integer.parseInt(input);
			    	                if (newQuantity > 0) {
			    	                    total -= price * oldQuantity;
			    	                    total += price * newQuantity;
			    	                    model.setValueAt(newQuantity, i, 3);
			    	                    model.setValueAt(newQuantity * price, i, 5);
			    	                    tpTotal.setText(String.valueOf(total));
			    	                    isValidQuantity = true; 
			    	                } else {
			    	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    	                }
			    	            } catch (NumberFormatException ex) {
			    	                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    	            }
			    	        } else {
			    	            break; 
			    	        }
			    	    }
			    	}

			     else {
			         Object[] row = new Object[6];
			         row[0] = id; 
			         row[1] = selectedItem;
			         row[2] = price; 
			         row[3] = quantity;
			         row[4] = donVi;
			         row[5] = quantity*price;
			         model.addRow(row);
			         total += price * quantity;
			     }
			     tpTotal.setText(String.valueOf(total));
			}
		});
		panel2.add(btnChoose);
		
	
		JButton btnConfirm = new JButton("Chốt đơn");
		btnConfirm.setBackground(new Color(224, 255, 255));
		btnConfirm.setForeground(new Color(75, 0, 130));
		btnConfirm.setBorder(null);
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnConfirm.setBounds(570, 221, 150, 40);
		btnConfirm.setBorder(new RoundedBorder(20));
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
		Tien.setBounds(461, 600, 100, 25);
		panel2.add(Tien);
		
		tpTotal = new JTextPane();
		tpTotal.setForeground(new Color(75, 0, 130));
		tpTotal.setEditable(false);
		tpTotal.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		tpTotal.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		tpTotal.setBounds(570, 600, 150, 25);
		panel2.add(tpTotal);
		
		JLabel lblNewLabel_2 = new JLabel("GỌI MÓN");
		lblNewLabel_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_2.setBounds(1039, 30, 150, 40);
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
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(279, 30, 128, 40);
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
			                    	model.setRowCount(0);
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
			                	        billID = resultSet.getInt("bill_ID");			                	
			                	        String sqlItems = "SELECT Item_ID, quantity FROM order_details WHERE bill_id = ?";
			                	        PreparedStatement pstmtItems = conn.prepareStatement(sqlItems);
			                	        pstmtItems.setInt(1, billID);
			                	        ResultSet resultSetItems = pstmtItems.executeQuery();

			                	        // Duyệt qua từng mục trong chi tiết đơn hàng
			                	        while (resultSetItems.next()) {
			                	            int itemID = resultSetItems.getInt("Item_ID");
			                	            int quantity = resultSetItems.getInt("quantity");

			                	            // Thực hiện truy vấn để lấy thông tin món ăn từ cơ sở dữ liệu
			                	            String sqlFoodDrink = "SELECT Name, price, DonVi FROM food_drink WHERE ID = ?";
			                	            PreparedStatement pstmtFoodDrink = conn.prepareStatement(sqlFoodDrink);
			                	            pstmtFoodDrink.setInt(1, itemID);
			                	            ResultSet resultSetFoodDrink = pstmtFoodDrink.executeQuery();

			                	            if (resultSetFoodDrink.next()) {
			                	                String selectedItem = resultSetFoodDrink.getString("Name");
			                	                int price = resultSetFoodDrink.getInt("price");
			                	                String donvi = resultSetFoodDrink.getString("DonVi");

			                	                Object[] row = new Object[6];
			                	                row[0] = itemID;
			                	                row[1] = selectedItem;
			                	                row[2] = price;
			                	                row[3] = quantity;
			                	                row[4] = donvi;
			                	                row[5] = quantity * price;
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
		
		JLabel lblVnd = new JLabel("VND");
		lblVnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblVnd.setForeground(new Color(75, 0, 130));
		lblVnd.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblVnd.setBounds(719, 600, 59, 25);
		panel2.add(lblVnd);
		
		JLabel lblnGivnd = new JLabel("Đơn giá (VND):");
		lblnGivnd.setForeground(new Color(75, 0, 130));
		lblnGivnd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblnGivnd.setBounds(77, 180, 126, 20);
		panel2.add(lblnGivnd);
		
		txtGia.setForeground(new Color(75, 0, 130));
		txtGia.setBackground(new Color(255, 255, 255));
		txtGia.setEditable(false);
		
		
	     
		txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtGia.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
		txtGia.setBounds(212, 177, 198, 25);
		panel2.add(txtGia);
		
		
		txtDonVi.setEditable(false);
		txtDonVi.setBorder(new LineBorder(new Color(169, 169, 169)));
		txtDonVi.setForeground(new Color(75, 0, 130));
		txtDonVi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDonVi.setBounds(279, 232, 96, 25);
		panel2.add(txtDonVi);
		txtDonVi.setColumns(10);
				
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
	         Statement stmt = conn.createStatement();
	         ResultSet resultSet = stmt.executeQuery("SELECT Name, Price, DonVi FROM food_drink WHERE classify = '" + classify + "' AND Status = 1")) {
	    	if(resultSet.first()) {
	    		int GIA = resultSet.getInt("Price");
	    		txtGia.setText(Integer.toString(GIA));
	    		txtDonVi.setText(resultSet.getString("DonVi"));
	            cbName.addItem(resultSet.getString("Name"));
	    	}
	        while (resultSet.next()) {
	            cbName.addItem(resultSet.getString("Name"));
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
	
	private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}