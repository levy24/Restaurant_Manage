package admin;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

//import data_cache.Food_Cache;
import data_cache.Drink_Cache;
public class drinks extends JPanel implements ActionListener{

    private JTable table;
    private DefaultTableModel tableModel;
    private String[] columnNames;
	private JButton btnSua, btnThem, btnXoa;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public drinks() throws SQLException {
		setBounds(0, 0, 666, 621);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbl1 = new JLabel("THỨC UỐNG");
		lbl1.setBackground(Color.LIGHT_GRAY);
		lbl1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lbl1.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl1);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		btnThem = new JButton("Thêm món");
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnThem);
        btnThem.addActionListener(this);
		
		btnSua = new JButton("Sửa món");
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnSua);
        btnSua.addActionListener(this);
		
		btnXoa = new JButton("Xóa món");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnXoa);
        btnXoa.addActionListener(this);
		
		new Drink_Cache();
		Object[][] data = new Object[Drink_Cache.Drink_ID.size()][4];
		for (int i = 0; i < Drink_Cache.Drink_ID.size(); i++) {
		    data[i][0] = Drink_Cache.Drink_ID.get(i); 
		    data[i][1] = Drink_Cache.Drink_Name.get(i); 
		    data[i][2] = Drink_Cache.Drink_Price.get(i); 
		    data[i][3] = Drink_Cache.Drink_Quantity.get(i);
		}

		columnNames = new String[]{"ID", "Tên thức uống", "Giá (VND)","Số lượng sẵn"}; 
        tableModel = new DefaultTableModel(data, columnNames); 
        table = new JTable(tableModel); 
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);   
	}

	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnThem) {
            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField priceField = new JTextField();
            JTextField quantityField = new JTextField();

            Object[] fields = {
                "ID:", idField,
                "Tên thức uống:", nameField,
                "Giá (VND):", priceField,
                "Số lượng sẵn có:", quantityField
            };

            int option = JOptionPane.showConfirmDialog(null, fields, "Nhập thông tin thức uống mới", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int price = Integer.parseInt(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                
                try {
					Drink_Cache.addDrink(id, name, price, quantity);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Lỗi khi thêm thức uống mới vào cơ sở dữ liệu: " + e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				    //e1.printStackTrace();
				}
                updateTable();
            }
        }
        else if (e.getSource() == btnSua) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
                int id = (int) table.getValueAt(selectedRow, 0);
                String name = (String) table.getValueAt(selectedRow, 1);
                int price = (int) table.getValueAt(selectedRow, 2);
                int quantity = (int) table.getValueAt(selectedRow, 3);

                JTextField idField = new JTextField(String.valueOf(id));
                JTextField nameField = new JTextField(name);
                JTextField priceField = new JTextField(String.valueOf(price));
                JTextField quantityField = new JTextField(String.valueOf(quantity));

                Object[] fields = {
                    "ID:", idField,
                    "Tên thức uống:", nameField,
                    "Giá (VND):", priceField,
                    "Số lượng sẵn có:", quantityField
                };

                int option = JOptionPane.showConfirmDialog(null, fields, "Chỉnh sửa thông tin thức uống", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    int newId = Integer.parseInt(idField.getText());
                    String newName = nameField.getText();
                    int newPrice = Integer.parseInt(priceField.getText());
                    int newQuantity = Integer.parseInt(quantityField.getText());
                    
                    try {
                        Drink_Cache.updateDrink(id, newId, newName, newPrice, newQuantity);
                        updateTable();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thức uống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một thức uống để chỉnh sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        else if(e.getSource() == btnXoa) {
        	JTextField idField = new JTextField();
            Object[] fields = {"Nhập ID thức uống cần xóa:", idField};
            int option = JOptionPane.showConfirmDialog(null, fields, "Xóa thức uống", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int id = Integer.parseInt(idField.getText());
                try {
                    Drink_Cache.deleteDrink(id);
                    updateTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi xóa thức uống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    // ex.printStackTrace();
                }
            }
        }
    }

    private void updateTable() {
        Object[][] newData = new Object[Drink_Cache.Drink_ID.size()][4];
        for (int i = 0; i < Drink_Cache.Drink_ID.size(); i++) {
            newData[i][0] = Drink_Cache.Drink_ID.get(i);
            newData[i][1] = Drink_Cache.Drink_Name.get(i);
            newData[i][2] = Drink_Cache.Drink_Price.get(i);
            newData[i][3] = Drink_Cache.Drink_Quantity.get(i);
        }
        tableModel.setDataVector(newData, columnNames);
        tableModel.fireTableDataChanged();
    }
}
