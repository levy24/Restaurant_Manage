package admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import data_cache.Employee_Cache;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public  class employee extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private static DefaultTableModel model;
	
	public employee() throws SQLException {
		
		setLayout(null);
		setSize(1540, 815);
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 1530, 815);
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lbl1 = new JLabel("Quản lý nhân viên");
		lbl1.setBounds(30, 71, 584, 70);
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
		panel.setBounds(581, 190, 432, 48);
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		panel.add(textField);
		textField.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(581, 248, 432, 48);
		panel_3.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1.add(lblTnNhnVin);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_1.setColumns(20);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin mới");
		lblNewLabel_1.setBounds(783, 110, 440, 55);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(581, 314, 432, 48);
		panel_3.add(panel_1_1);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1.add(lblSinThoi);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_2.setColumns(20);
		panel_1_1.add(textField_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBounds(1023, 190, 432, 48);
		panel_3.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(40, 10, 67, 26);
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1_1.add(lblaCh);
		
		textField_3 = new JTextField();
		textField_3.setBounds(176, 14, 246, 23);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_3.setColumns(20);
		panel_1_1_1.add(textField_3);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBounds(1023, 314, 432, 48);
		panel_3.add(panel_1_1_2);
		panel_1_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
		
		JLabel lblNewPassword = new JLabel("Mật khẩu");
		lblNewPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1_2.add(lblNewPassword);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_4.setColumns(20);
		panel_1_1_2.add(textField_4);
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setBounds(1023, 248, 432, 48);
		panel_3.add(panel_1_1_3);
		panel_1_1_3.setLayout(null);
		
		JLabel lblVaiTr = new JLabel("Vai trò");
		lblVaiTr.setBounds(30, 8, 63, 26);
		lblVaiTr.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_1_1_3.add(lblVaiTr);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(171, 11, 89, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Nhân viên"}));
		comboBox.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		panel_1_1_3.add(comboBox);
		
		JButton btnNewButton = new JButton("Thêm mới");
		btnNewButton.setBounds(83, 248, 168, 48);
		panel_3.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            
		            String empID = textField.getText();
		            String empName = textField_1.getText();
		            String empPhone = textField_2.getText();
		            String empAddress = textField_3.getText();
		            String empPosition = comboBox.getSelectedItem().toString();
		            String empPassword = textField_4.getText();

		            
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
		                    
		                    Object[] newRow = {empID, empName, empPhone, empAddress, empPosition, empPassword};
		                    Set_TableModel(newRow); 
		                   
		                    textField.setText("");
		                    textField_1.setText("");
		                    textField_2.setText("");
		                    textField_3.setText("");
		                    textField_4.setText("");
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(30, 190, 541, 48);
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		panel_2.add(lblTmKim);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textField_5.setColumns(20);
		panel_2.add(textField_5);
		
		JButton btnNewButton_1 = new JButton("Tìm ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1) {
					Chuc_Nang.Tim_NV(textField_5.getText());
				}
			}
		});
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		           
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow == -1) {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
		            } else {	            
		                model.removeRow(selectedRow);
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnXa.setBounds(83, 314, 168, 48);
		panel_3.add(btnXa);
		btnXa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(340, 248, 168, 48);
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
		
		JButton btntLi = new JButton("Đặt lại");
		btntLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btntLi) {
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
				}
			}
		});
		btntLi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btntLi.setBounds(340, 314, 168, 48);
		panel_3.add(btntLi);
		
	}
	public static void Set_TableModel(Object[] data) {
		model.setRowCount(0);
		model.addRow(data);
	}
	private static void Show_Drink() {
		if(model.getRowCount() >0) {
			model.setRowCount(0);
		}
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


