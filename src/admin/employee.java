package admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data_cache.Employee_Cache;
import font.RoundedBorder;
import security.Crypt;

import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import org.bouncycastle.jcajce.spec.KTSParameterSpec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public  class employee extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtPass;
	private JTextField txtTimNV;
	public static DefaultTableModel model;
	
	public employee() throws SQLException {
		
		setLayout(null);
		setSize(1540, 815);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(253, 245, 230));
		panel_3.setBounds(0, 0, 1530, 815);
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lbl1 = new JLabel("Quản lý nhân viên");
		lbl1.setBounds(44, 68, 501, 70);
		panel_3.add(lbl1);
		lbl1.setBackground(Color.LIGHT_GRAY);
		lbl1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lbl1.setForeground(UIManager.getColor("Button.disabledForeground"));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		JTable table = new JTable();
		
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFocusTraversalPolicyProvider(true);
		scrollPane.setFocusCycleRoot(true);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(57, 410, 1398, 364);
		panel_3.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 222, 173));
		panel.setBounds(581, 190, 432, 48);
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel.add(lblNewLabel);
		
		txtMa = new JTextField();
		txtMa.setHorizontalAlignment(SwingConstants.CENTER);
		txtMa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		panel.add(txtMa);
		txtMa.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 222, 173));
		panel_1.setBounds(581, 248, 432, 48);
		panel_3.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setForeground(new Color(128, 128, 128));
		lblTnNhnVin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1.add(lblTnNhnVin);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_1.setColumns(20);
		panel_1.add(textField_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(255, 222, 173));
		panel_1_1.setBounds(581, 314, 432, 48);
		panel_3.add(panel_1_1);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setForeground(new Color(128, 128, 128));
		lblSinThoi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1.add(lblSinThoi);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_2.setColumns(20);
		panel_1_1.add(textField_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(255, 222, 173));
		panel_1_1_1.setBounds(1023, 190, 432, 48);
		panel_3.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setForeground(new Color(128, 128, 128));
		lblaCh.setBounds(40, 10, 67, 26);
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1_1.add(lblaCh);
		
		textField_3 = new JTextField();
		textField_3.setBounds(152, 14, 270, 23);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_3.setColumns(20);
		panel_1_1_1.add(textField_3);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBackground(new Color(255, 222, 173));
		panel_1_1_2.setBounds(1023, 314, 432, 48);
		panel_3.add(panel_1_1_2);
		panel_1_1_2.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("Mật khẩu");
		lblNewPassword.setBounds(35, 10, 90, 26);
		lblNewPassword.setForeground(new Color(128, 128, 128));
		lblNewPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1_2.add(lblNewPassword);
		
		txtPass = new JTextField();
		txtPass.setBounds(152, 14, 270, 23);
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txtPass.setColumns(20);
		panel_1_1_2.add(txtPass);
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setBackground(new Color(255, 222, 173));
		panel_1_1_3.setBounds(1023, 248, 432, 48);
		panel_3.add(panel_1_1_3);
		panel_1_1_3.setLayout(null);
		
		JLabel lblVaiTr = new JLabel("Vai trò");
		lblVaiTr.setBounds(30, 8, 63, 26);
		lblVaiTr.setForeground(new Color(128, 128, 128));
		lblVaiTr.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1_3.add(lblVaiTr);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(152, 11, 113, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Nhân viên"}));
		comboBox.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		panel_1_1_3.add(comboBox);
		
		JButton btnNewButton = new JButton("Thêm mới");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new RoundedBorder(20));
		btnNewButton.setForeground(new Color(128, 128, 128));
		btnNewButton.setIcon(new ImageIcon(employee.class.getResource("/image/Hopstarter-Button-Button-Add.16.png")));
		btnNewButton.setBounds(90, 262, 177, 39);
		panel_3.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Boolean kiemtraBoolean = true;
		            String empID = txtMa.getText();
		            String empName = textField_1.getText();
		            String empPhone = textField_2.getText();
		            try {
		            	if(empID.length() > 6) {
		            		JOptionPane.showMessageDialog(null, "Mã nhân viên phải ít hơn 7 ký tự!");
		            		kiemtraBoolean = false;
		            	}
		            	int x = Integer.parseInt(empID);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số nguyên!");
						kiemtraBoolean =false;
					}
		            try {
		            	int x = Integer.parseInt(empPhone);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
						kiemtraBoolean =false;
						
					}
		            String empAddress = textField_3.getText();
		            //String tempString = comboBox.getSelectedItem().toString();
		            String empPosition = (comboBox.getSelectedIndex() == 0) ? "Admin" : "Employee";
		            String empPassword = "";
		            try {
						
						empPassword = Crypt.encrypt( txtPass.getText());
					} catch (Exception e2) {
						// TODO: handle exception
					} 
		            
		            
		            if (empID.isEmpty() || empName.isEmpty() || empPhone.isEmpty() || empAddress.isEmpty() || empPassword.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
		            } else {
		                
		                boolean idExists = false; 
		                int rowCount = model.getRowCount();
		                for (int i = 0; i < rowCount; i++) {
		                    if (empID.equals(model.getValueAt(i, 0))) {
		                        idExists = true;
		                        break;
		                    }
		                }
		                if (idExists) {
		                    JOptionPane.showMessageDialog(null, "ID đã tồn tại. Vui lòng chọn ID khác.");
		                } else {
		                    
//		                    Object[] newRow = {empID, empName, empPhone, empAddress, empPosition, empPassword};
//		                    Set_TableModel(newRow); 
		                	if(kiemtraBoolean) {
		                		E_CN.insertData(empID, empName, empPhone, empAddress, empPosition, empPassword);
			                	Show_Drink();
			                    txtMa.setText("");
			                    textField_1.setText("");
			                    textField_2.setText("");
			                    textField_3.setText("");
			                    txtPass.setText("");
		                	}
		                	
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		JButton btnXa = new JButton("Xóa nhân viên");
		btnXa.setBackground(new Color(255, 255, 255));
		btnXa.setForeground(new Color(128, 128, 128));
		btnXa.setBorder(new RoundedBorder(20));
		btnXa.setIcon(new ImageIcon(employee.class.getResource("/image/Hopstarter-Button-Button-Delete.16.png")));
		btnXa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		        	String empID = txtMa.getText();
		            
		            if (empID.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần xóa");
		            } else {	     
		            	int choice = JOptionPane.showConfirmDialog(null, "Xác nhận xóa dữ liệu nhân viên?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
		   				 if (choice == JOptionPane.OK_OPTION) {
		   					E_CN.deleteData(txtMa.getText());
		   				 }            
		            }
		        } catch (Exception ex) {
		           
		        }
		    }
		});

		btnXa.setBounds(90, 314, 177, 39);
		panel_3.add(btnXa);
		btnXa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton btnSa = new JButton("Sửa thông tin");
		btnSa.setBackground(new Color(255, 255, 255));
		btnSa.setForeground(new Color(128, 128, 128));
		btnSa.setBorder(new RoundedBorder(20));
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {
		            
		            String empID = txtMa.getText();
		            String empName = textField_1.getText();
		            String empPhone = textField_2.getText();
		            String empAddress = textField_3.getText();
		            String empPosition = (comboBox.getSelectedIndex() == 0) ? "Admin" : "Employee";
		            String empPassword = "";
		            try {
						
						empPassword = Crypt.encrypt( txtPass.getText());
					} catch (Exception e2) {
						// TODO: handle exception
					} 
		            
		            
		            if (empID.isEmpty() || empName.isEmpty() || empPhone.isEmpty() || empAddress.isEmpty() || empPassword.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
		            } else {
		                
		                boolean idExists = false; 
		                int rowCount = model.getRowCount();
		                for (int i = 0; i < rowCount; i++) {
		                    if (empID.equals(model.getValueAt(i, 0))) {
		                        idExists = true;
		                        break;
		                    }
		                }
		                if (idExists) {
		                    JOptionPane.showMessageDialog(null, "ID đã tồn tại. Vui lòng chọn ID khác.");
		                } else {
		                    
//		                    Object[] newRow = {empID, empName, empPhone, empAddress, empPosition, empPassword};
//		                    Set_TableModel(newRow); 
		                	int choice = JOptionPane.showConfirmDialog(null, "Xác nhận sửa dữ liệu nhân viên?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
		   				 if (choice == JOptionPane.OK_OPTION) {
		   					 E_CN.updateData(empID, empName, empPhone, empAddress, empPosition, empPassword);
		   				 }
		                	
		                	Show_Drink();
		                    txtMa.setText("");
		                    textField_1.setText("");
		                    textField_2.setText("");
		                    textField_3.setText("");
		                    txtPass.setText("");
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
				
			}
		});
		btnSa.setIcon(new ImageIcon(employee.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png")));
		btnSa.setBounds(318, 262, 161, 39);
		panel_3.add(btnSa);
		btnSa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		new Employee_Cache();
		
		int size = Employee_Cache.Emp_ID.size();
	    Object[][] data = new Object[size][6]; 
	    for (int i = 0; i < size; i++) {
	            data[i][0] = Employee_Cache.Emp_ID.get(i);
	            data[i][1] = Employee_Cache.Emp_Name.get(i);
	            data[i][2] = Employee_Cache.Emp_Phone.get(i);
	            data[i][3] = Employee_Cache.Emp_Address.get(i);
	            data[i][4] = Employee_Cache.Emp_Position.get(i);
	            data[i][5] = Employee_Cache.Emp_Pass.get(i);
	        }
		
        String[] columnNames = {"ID", "Tên nhân viên", "Số điện thoại","Địa chỉ","Vai trò","Password"};
		
		ButtonGroup genderGroup = new ButtonGroup();
		model = new DefaultTableModel(data,columnNames);
		table.setModel(model);
		
		table.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {        
		            int selected = table.getSelectedRow();
		            if(selected != -1) {
		                Object value_ID = table.getValueAt(selected, 0); 
		                Object value_Name = table.getValueAt(selected, 1); 
		                Object value_Phone = table.getValueAt(selected, 2); 
		                Object value_Address = table.getValueAt(selected, 3); 
		                Object value_Position = table.getValueAt(selected, 4); 
		                Object value_Password = table.getValueAt(selected, 5); 
		                 txtMa.setText(value_ID.toString()); 
		                textField_1.setText(value_Name.toString());
		                textField_2.setText(value_Phone.toString());
		                textField_3.setText(value_Address.toString());
		                comboBox.setSelectedItem(value_Position.toString());
		                txtPass.setText(value_Password.toString());
		            }
		        }
		    }
		});

		JButton btntLi = new JButton("Đặt lại");
		btntLi.setBackground(new Color(255, 255, 255));
		btntLi.setForeground(new Color(128, 128, 128));
		btntLi.setBorder(new RoundedBorder(20));
		btntLi.setIcon(new ImageIcon(employee.class.getResource("/image/Aniket-Suvarna-Box-Regular-Bx-reset.16.png")));
		btntLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btntLi) {
					txtMa.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					txtPass.setText("");
					txtTimNV.setText("");
					Show_Drink();
				}
			}
		});
		btntLi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btntLi.setBounds(318, 314, 161, 39);
		panel_3.add(btntLi);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(253, 245, 230));
		panel_4.setBounds(555, 105, 923, 278);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin mới");
		lblNewLabel_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1.setBackground(new Color(244, 164, 96));
		lblNewLabel_1.setBounds(372, 24, 178, 43);
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setForeground(new Color(128, 128, 128));
		lblTmKim.setBounds(67, 195, 86, 26);
		panel_3.add(lblTmKim);
		lblTmKim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		
		txtTimNV = new JTextField();
		txtTimNV.setBounds(163, 199, 246, 23);
		panel_3.add(txtTimNV);
		txtTimNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimNV.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txtTimNV.setColumns(20);
		
		JButton btnNewButton_1 = new JButton("Tìm ");
		btnNewButton_1.setForeground(new Color(128, 128, 128));
		btnNewButton_1.setBorder(new RoundedBorder(20));
		btnNewButton_1.setBounds(419, 197, 86, 27);
		panel_3.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Chuc_Nang.Tim_kiem_NV(txtTimNV.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblNewLabel_5 = new JLabel("Quản lý");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_5.setBounds(1401, 20, 119, 53);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(employee.class.getResource("/image/Hopstarter-Sleek-Xp-Basic-Preppy.48.png")));
		lblNewLabel_2.setBounds(1350, 10, 48, 67);
		panel_3.add(lblNewLabel_2);
		
	}
	public static void Set_TableModel(Object[] data) {
		model.setRowCount(0);
		model.addRow(data);
	}
	private static void Show_Drink() {
		if(model.getRowCount() >0) {
			model.setRowCount(0);
		}
		data_cache.Employee_Cache.reloadData();
		Object[] data = new Object[6]; 
	    for (int i = 0; i < Employee_Cache.Emp_ID.size(); i++) {
	            data[0] = Employee_Cache.Emp_ID.get(i);
	            data[1] = Employee_Cache.Emp_Name.get(i);
	            data[2] = Employee_Cache.Emp_Phone.get(i);
	            data[3] = Employee_Cache.Emp_Address.get(i);
	            data[4] = Employee_Cache.Emp_Position.get(i);
	            data[5] = Employee_Cache.Emp_Pass.get(i);
	            model.addRow(data);
	        }
	}
}