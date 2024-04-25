package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import data_cache.Employee_Cache;

public class employee extends JPanel {

	private static final long serialVersionUID = 1L;

	public employee() throws SQLException {
		setBounds(0, 0, 666, 621);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbl1 = new JLabel("NHÂN VIÊN");
		lbl1.setBackground(Color.LIGHT_GRAY);
		lbl1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lbl1.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl1);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JButton btnThem = new JButton("Thêm nhân viên");
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnThem);
		
		JButton btnSua = new JButton("Sửa thông tin nhân viên");
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa nhân viên");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnXoa);
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
		
        String[] columnNames = {"ID", "Tên nhân viên", "Số điện thoại","Địa chỉ","Vị trí","Pass"};
        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);	
	}
}


