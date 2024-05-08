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

import connectDTB.connect;
import data_cache.Bill_Cache;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;
import login.dangnhap1;
import staff.Bill;

public class Goimon extends JPanel {

	private static final long serialVersionUID = 1L;

	public int kt;
	public boolean ktbtn[] = new boolean[25];
	public Button button[] = new Button[25];
	boolean isFoodSelected;
	long total = 0;
	JTextPane textPane;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Goimon() {
		int n = 25;
		setBounds(0, 0, 1540, 815);
		Color gr = new Color(0,255,128);
		Color re = new Color(220,20,60);
		Color ye = new Color(255, 255, 128);
		
		
		setForeground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		for (int i=0;i<n;i++)
			ktbtn[i] = true;
		
		JLabel lblNewLabel = new JLabel("ORDER");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1550, 50);
		add(lblNewLabel);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(128, 128, 192));
		panel2.setBounds(700, 100, 825, 650);	
		add(panel2);
		panel2.setLayout(null);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chọn loại", "Thức ăn", "Đồ uống"}));
		comboBox.setBounds(200, 70, 150, 21);
		panel2.add(comboBox);
		
		JLabel classify = new JLabel("Loại:");
		classify.setFont(new Font("Times New Roman", Font.BOLD, 17));
		classify.setBounds(100, 70, 45, 20);
		panel2.add(classify);
		
		JLabel Name = new JLabel("Chọn món:");
		Name.setFont(new Font("Times New Roman", Font.BOLD, 17));
		Name.setBounds(100, 125, 90, 20);
		panel2.add(Name);
		
		JLabel Quantity = new JLabel("Số lượng:");
		Quantity.setFont(new Font("Times New Roman", Font.BOLD, 17));
		Quantity.setBounds(100, 180, 70, 20);
		panel2.add(Quantity);
		
		JLabel Table = new JLabel("Số bàn:");
		Table.setFont(new Font("Times New Roman", Font.BOLD, 17));
		Table.setBounds(100, 235, 70, 20);
		panel2.add(Table);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Chọn món ăn"}));
		//Đổ dữ liệu lên chọn món khi có loại 
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        String selectedType = (String)cb.getSelectedItem();
				// Kiểm tra loại đã chọn và cập nhật biến boolean tương ứng
		        if (selectedType.equals("Thức ăn")) {
		            isFoodSelected = true;
		        } else if (selectedType.equals("Đồ uống")) {
		            isFoodSelected = false;
		        }      
		        // Xóa các mục trong ComboBox chọn món
		        comboBox_1.removeAllItems();
		        if (isFoodSelected) {
		    		try {
		    			//khởi tạo đối tượng để lấy dữ liệu từ cơ sở dữ liệu 
		    			Food_Cache foodCache = new Food_Cache();
		    		} catch (SQLException e1) {
		    			e1.printStackTrace();
		    		}
		    		for (String foodName : Food_Cache.FName) {
		    		    comboBox_1.addItem(foodName);
		    		}
		        } else {
		    		try {
		    			Drink_Cache drinkCache = new Drink_Cache();
		    		} catch (SQLException e2) {
		    			e2.printStackTrace();
		    		}
		    		for (String drinkName : Drink_Cache.Drink_Name) {
		    		    comboBox_1.addItem(drinkName);
		    		}
		        }
		    }
		});
		comboBox_1.setBounds(200, 125, 150, 21);
		panel2.add(comboBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		spinner.setBounds(200, 180, 50, 25);
		panel2.add(spinner);
		
		JTextPane textpane = new JTextPane();
		textpane.setBackground(new Color(255, 255, 255));
		textpane.setEditable(false);
		textpane.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textpane.setBounds(200, 235, 50, 25);
		panel2.add(textpane);
		

		JTable table = new JTable();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Tên món");
		model.addColumn("Giá (VND)");
		model.addColumn("Số lượng");
		table.setModel(model);
        
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 320, 800, 250);
		panel2.add(scrollPane);
		
		JButton btnChon = new JButton("Chọn món");
		btnChon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnChon.setBounds(570, 150, 150, 40);
		btnChon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				 String selectedItem = (String) comboBox_1.getSelectedItem(); 
			     int quantity = (int) spinner.getValue(); 

			     int id = -1;
			     int price = -1;
			     if (isFoodSelected)
			     {
			    	 for (int i = 0; i < Food_Cache.FName.size(); i++) {
				    	 if(Food_Cache.FName.get(i).equals(selectedItem))
				    	 {
				    		id = Food_Cache.FID.get(i);
				    		price = Food_Cache.FPrice.get(i);
				    		break;
				    	 }
				     }
			     }
			     else {
			    	 for (int i = 0; i < Drink_Cache.Drink_Name.size(); i++) {
				    	 if(Drink_Cache.Drink_Name.get(i).equals(selectedItem))
				    	 {
				    		id = Drink_Cache.Drink_ID.get(i);
				    		price = Drink_Cache.Drink_Price.get(i);
				    		break;
				    	 }
				     }
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
			         Object[] row = new Object[4];
			         row[0] = id; 
			         row[1] = selectedItem;
			         row[2] = price; 
			         row[3] = quantity;
			         model.addRow(row);
			         total += price * quantity;
			     }
			     textPane.setText(String.valueOf(total));
			}
		});
		panel2.add(btnChon);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnSua.setBounds(570, 70, 150, 40);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex != -1) {
				    String selectedFoodName = (String) table.getValueAt(selectedRowIndex, 1);
				    String input = JOptionPane.showInputDialog(null, "Nhập số lượng mới cho món " + selectedFoodName + ":", "Sửa số lượng", JOptionPane.PLAIN_MESSAGE);
				    if (input != null && !input.isEmpty()) {
				        try {
				            int oldQuantity = (int) model.getValueAt(selectedRowIndex, 3);
				            int price = (int) model.getValueAt(selectedRowIndex, 2);
				            int newQuantity = Integer.parseInt(input);
				            total -= price * oldQuantity;
				            total += price * newQuantity;
				            model.setValueAt(newQuantity, selectedRowIndex, 3);
				            textPane.setText(String.valueOf(total));
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				} else {
				    JOptionPane.showMessageDialog(null, "Vui lòng chọn một món ăn để có thể sửa số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel2.add(btnSua);
		
//		JButton btnChotDon = new JButton("Chốt đơn");
//		btnChotDon.setFont(new Font("Times New Roman", Font.BOLD, 17));
//		btnChotDon.setBounds(570, 225, 150, 40);
//		btnChotDon.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				if (model.getRowCount() == 0) {
//		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn món ăn nào.", "Thông báo", JOptionPane.WARNING_MESSAGE);
//		            return;
//		        }
//				
//				
//				int tableID = Integer.parseInt(textpane.getText());
//		        String time = getTimeFromSystem(); 
//		        //String empID = loginObject.getLoggedInUserID();
//		        String empID = "171200";
//		        long totalBill = total;
//		        Bill_Cache billCache = new Bill_Cache();
//		        int currentBillID = getCurrentBillID(); 
//		        boolean success = billCache.addBill(currentBillID, time, totalBill, tableID, empID, 0);
//		        if (!success) {
//		            JOptionPane.showMessageDialog(null, "Lỗi khi thêm hóa đơn vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
//		            return;
//		        }
//
//		        for (int i = 0; i < model.getRowCount(); i++) {
//		            int itemID = (int) model.getValueAt(i, 0);
//		            int quantity = (int) model.getValueAt(i, 3);
//		            int price = (int) model.getValueAt(i, 2)*quantity;
//		            success = billCache.addOrderDetail(currentBillID, itemID, quantity, price);
//		            if(isFoodSelected)
//						try {
//							Food_Cache.updateQuantity(itemID, quantity);
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					else
//						try {
//							Drink_Cache.updateQuantity(itemID, quantity);
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//		            if (!success) {
//		                JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết đơn hàng vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
//		                return;
//		            }
//		        }
//		      
//		        JOptionPane.showMessageDialog(null, "Hóa đơn đã được chốt thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//		        model.setRowCount(0);
//		        total = 0; 
//		        textPane.setText("0");
//		    
//				int k = Integer.parseInt(textpane.getText());
//				ktbtn[k-1]=false;
//				button[k-1].setBackground(re);
//				textpane.setText("");
//			}
//		
//		});
		JButton btnChotDon = new JButton("Chốt đơn");
		btnChotDon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnChotDon.setBounds(570, 225, 150, 40);
		btnChotDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (model.getRowCount() == 0) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn món ăn nào.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
				int tableID = Integer.parseInt(textpane.getText());
		        String time = getTimeFromSystem(); 
		        int empID =  Integer.parseInt(dangnhap1.loggedInUserID);
		        long totalBill = total;
		        Bill_Cache billCache = new Bill_Cache();
		        int currentBillID = getCurrentBillID(); 
		        boolean success = billCache.addBill(currentBillID, time,tableID, empID, totalBill,  0);
		        if (!success) {
		            JOptionPane.showMessageDialog(null, "Lỗi khi thêm hóa đơn vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        for (int i = 0; i < model.getRowCount(); i++) {
		            int itemID = (int) model.getValueAt(i, 0);
		            int quantity = (int) model.getValueAt(i, 3);
		            int price = (int) model.getValueAt(i, 2)*quantity;
		            success = billCache.addOrderDetail(currentBillID, itemID, quantity, price);
		            if(isFoodSelected)
						try {
							Food_Cache.updateQuantity(itemID, quantity);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else
						try {
							Drink_Cache.updateQuantity(itemID, quantity);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            if (!success) {
		                JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết đơn hàng vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        }
		        connect connector = new connect();
				Connection conn = connector.connection;
				Statement stmt = null;
				try {
					stmt = conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        if (success) {
		        	  String updateTableQuery = "UPDATE tables SET Status = 1 WHERE Table_ID = " + tableID; 
		        	  try {
						stmt.executeUpdate(updateTableQuery);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		      
		        JOptionPane.showMessageDialog(null, "Hóa đơn đã được chốt thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        model.setRowCount(0);
		        total = 0; 
		        textPane.setText("0");
		    
				int k = Integer.parseInt(textpane.getText());
				ktbtn[k-1]=false;
				button[k-1].setBackground(re);
				
				textpane.setText("");
//				Bill bill = new Bill();
//				
				Bill.updateTable();
			}
		
		});
		panel2.add(btnChotDon);
		
		JLabel Tien = new JLabel("Tổng tiền:");
		Tien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Tien.setHorizontalAlignment(SwingConstants.CENTER);
		Tien.setBounds(183, 600, 100, 25);
		panel2.add(Tien);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textPane.setBounds(300, 600, 150, 25);
		panel2.add(textPane);
		
		JLabel lblNewLabel_2 = new JLabel("GỌI MÓN");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(1040, 55, 128, 40);
		add(lblNewLabel_2);
		
		//Panel chọn bàn
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(10, 100, 650, 650);
		add(panel);
		panel.setLayout(new GridLayout(5, 5, 10, 10));
		
		JLabel lblNewLabel_1 = new JLabel("BÀN ĂN");
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
			    button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
			    button[i].setBackground(gr);
			    panel.add(button[i]);
			    if(tableStatus == 1) 
			        button[i].setBackground(Color.RED); 
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
			                        textpane.setText(String.valueOf(currentIndex + 1));
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
			            } else {
			                JOptionPane.showMessageDialog(null, "Bàn này đang có khách!!!");
			            }
			        }
			    });
			    i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panel2.add(btnChotDon);
				
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
	            if (!rs.wasNull()) {
	                currentBillID++; 
	            }
	        }
	        rs.close();
	        pstmt.close();
	        con.close();
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
	
}
