package admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDTB.connect;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashSet;

public class CTC extends JFrame implements ActionListener {

    public static JFrame frame = new JFrame();;
    private static JMenuBar menuBar;
    private static JPanel panel;
    private static DefaultTableModel tableModel;
    
    public food faFood; 
    public drinks dr; 
    public employee ep;
    private JTable tbFood;
    private JTextField txtTim;
    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtGia;
    private JComboBox comboBox;
    
    public void GUI() {
    	 frame.setTitle("ADMIN");
         frame.setBounds(0, 0, 1900, 1000);
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         menuBar = new JMenuBar();
         frame.setJMenuBar(menuBar);

         JMenu mnQuanLy = new JMenu("Quản lý");
         mnQuanLy.setIcon(new ImageIcon(CTC.class.getResource("/image/Papirus-Team-Papirus-Apps-System-file-manager.24.png")));
         menuBar.add(mnQuanLy);

         JMenuItem mntmMonAn = new JMenuItem("Thực đơn");
         mntmMonAn.setIcon(new ImageIcon(CTC.class.getResource("/image/Jamespeng-Cuisine-Pork-Chop-Set.24.png")));
         mnQuanLy.add(mntmMonAn);
         mntmMonAn.addActionListener(this);

         JMenuItem mntmNhanVien = new JMenuItem("Nhân viên");
         mntmNhanVien.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Sleek-Xp-Basic-Preppy.24.png")));
         mnQuanLy.add(mntmNhanVien);
         
         JMenu mnNewMenu = new JMenu("Thống kê");
         mnNewMenu.setIcon(new ImageIcon(CTC.class.getResource("/image/Awicons-Vista-Artistic-Chart.24.png")));
         menuBar.add(mnNewMenu);
         
         JMenuItem mntmDoanhthu = new JMenuItem("Doanh thu");
         mntmDoanhthu.setIcon(new ImageIcon(CTC.class.getResource("/image/Designcontest-Ecommerce-Business-Dollar.24.png")));
         mnNewMenu.add(mntmDoanhthu);
         
         JMenuItem mntmXuatThongKe = new JMenuItem("Xuất thống kê");
         mnNewMenu.add(mntmXuatThongKe);
         
         JMenu mnTuyChon = new JMenu("Tùy chọn");
         mnTuyChon.setIcon(new ImageIcon(CTC.class.getResource("/image/Dtafalonso-Android-Lollipop-Settings.24.png")));
         menuBar.add(mnTuyChon);
         
         JMenuItem mntmThoat = new JMenuItem("Thoát");
         mnTuyChon.add(mntmThoat);
         mntmThoat.addActionListener(this);
        frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
         
        panel = new JPanel();
        
         frame.getContentPane().add(panel);
         panel.setLayout(null);
         String[] columns =  {
      		"Id", "Tên món", "Giá tiền", "Số lượng có sẵn"
      	};
         tableModel = new DefaultTableModel();
         tableModel.setColumnIdentifiers(columns);
         tbFood = new JTable();
         tbFood.setFont(new Font("Times New Roman", Font.PLAIN, 18));
         
         tbFood.setCellSelectionEnabled(true);
         tbFood.setColumnSelectionAllowed(true);
         tbFood.setFillsViewportHeight(true);
         tbFood.setBackground(new Color(224, 255, 255));
         tbFood.setBounds(800, 10, 600, 600);
         tbFood.setModel(tableModel);
         JScrollPane scrollPane = new JScrollPane(tbFood);
         scrollPane.setSize(700, 500);
         scrollPane.setLocation(830, 100);
         panel.add(scrollPane);
         
         JLabel lblNewLabel = new JLabel("Quản lý thực đơn");
         lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
         lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel.setBounds(215, 36, 452, 78);
         panel.add(lblNewLabel);
         
         JPanel panel_1 = new JPanel();
         panel_1.setBounds(50, 222, 419, 53);
         panel.add(panel_1);
         panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
         JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
         lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
         panel_1.add(lblNewLabel_1);
         
         txtTim = new JTextField();
         txtTim.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         panel_1.add(txtTim);
         txtTim.setColumns(20);
         
         JPanel panel_1_1 = new JPanel();
         panel_1_1.setBounds(50, 285, 419, 53);
         panel.add(panel_1_1);
         panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
         JLabel lblNewLabel_1_1 = new JLabel("Mã món");
         lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         panel_1_1.add(lblNewLabel_1_1);
         
         txtMa = new JTextField();
         txtMa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         txtMa.setColumns(20);
         panel_1_1.add(txtMa);
         
         JPanel panel_1_2 = new JPanel();
         panel_1_2.setBounds(50, 348, 419, 53);
         panel.add(panel_1_2);
         panel_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
         JLabel lblNewLabel_1_2 = new JLabel("Tên món");
         lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         panel_1_2.add(lblNewLabel_1_2);
         
         txtTen = new JTextField();
         txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         txtTen.setColumns(20);
         panel_1_2.add(txtTen);
         
         JPanel panel_1_3 = new JPanel();
         panel_1_3.setBounds(50, 411, 419, 53);
         panel.add(panel_1_3);
         panel_1_3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
         JLabel lblNewLabel_1_3 = new JLabel("Giá thành");
         lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         panel_1_3.add(lblNewLabel_1_3);
         
         txtGia = new JTextField();
         txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         txtGia.setColumns(20);
         panel_1_3.add(txtGia);
         
         JPanel panel_1_4 = new JPanel();
         panel_1_4.setBounds(50, 474, 419, 53);
         panel.add(panel_1_4);
         panel_1_4.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
         JLabel lblNewLabel_1_4 = new JLabel("Số lượng");
         lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         panel_1_4.add(lblNewLabel_1_4);
         
         JSpinner spinner = new JSpinner();
         spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), null, null, Integer.valueOf(10)));
         spinner.setPreferredSize(new Dimension(60, 30));
         spinner.setFont(new Font("Times New Roman", Font.BOLD, 20));
         panel_1_4.add(spinner);
         
         JLabel lblDanhSchMn = new JLabel("Danh sách món");
         lblDanhSchMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
         lblDanhSchMn.setBounds(830, 12, 452, 78);
         panel.add(lblDanhSchMn);
         
         JButton btnNewButton = new JButton("Thêm món");
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		String maMon = txtMa.getText().trim();
                String tenMon = txtTen.getText().trim();
                String giaTienStr = txtGia.getText().trim();
                String soLuongStr = spinner.getValue().toString();
                int soLuong = Integer.parseInt(soLuongStr);
                if (maMon.isEmpty() || tenMon.isEmpty() || giaTienStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra xem maMon có phải là số không
                int maMonInt;
                try {
                    maMonInt = Integer.parseInt(maMon);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã món phải là một số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra xem giá tiền có phải là số không
                int giaTien;
                try {
                    giaTien = Integer.parseInt(giaTienStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Giá tiền phải là một số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (maMonInt == (int) tableModel.getValueAt(i, 0)) {
                        JOptionPane.showMessageDialog(null, "Mã món đã tồn tại trong bảng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                Object[] newRowData = { maMonInt, tenMon, giaTien, soLuong};
                tableModel.addRow(newRowData);   

                // Sau khi thêm món, có thể làm sạch các text field
                txtMa.setText("");
                txtTen.setText("");
                txtGia.setText("");
                spinner.setValue(0);

                JOptionPane.showMessageDialog(null, "Thêm món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
         	
         });
         btnNewButton.setBackground(new Color(124, 252, 0));
         btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnNewButton.setBounds(508, 285, 188, 39);
         panel.add(btnNewButton);
         
         JButton btnXaMn = new JButton("Xóa món");
         btnXaMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnXaMn.setBackground(new Color(255, 0, 0));
         btnXaMn.setBounds(508, 348, 188, 39);
         panel.add(btnXaMn);
         
         JButton btnSaMn = new JButton("Sửa món");
         btnSaMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnSaMn.setBackground(new Color(255, 215, 0));
         btnSaMn.setBounds(508, 411, 188, 39);
         panel.add(btnSaMn);
         
         JButton btnTmKim = new JButton("Tìm");
         btnTmKim.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		if(e.getSource() == btnTmKim) {
         			String selectedOption = comboBox.getSelectedItem().toString();
         			
         			if (selectedOption.equals("Thức ăn")) {
             			Chuc_Nang.Tim_kiem(txtTim.getText());
         			}
         			if (selectedOption.equals("Đồ uống")) {
             			Chuc_Nang.Tim_kiem2(txtTim.getText());
         			}
         		}
         	}
         });
         btnTmKim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnTmKim.setBackground(new Color(245, 255, 250));
         btnTmKim.setBounds(508, 222, 188, 39);
         panel.add(btnTmKim);
         
         JButton btntLi = new JButton("Đặt lại");
         btntLi.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		if(e.getSource() == btntLi) {
         			txtTim.setText("");
         			txtTen.setText("");
         			txtGia.setText("");
         			txtMa.setText("");
         		}
         	}
         });
         btntLi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btntLi.setBackground(new Color(245, 255, 250));
         btntLi.setBounds(154, 620, 188, 39);
         panel.add(btntLi);
         
         JButton btnThot = new JButton("Thoát");
         btnThot.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		if(e.getSource() == btnThot) {
         			int choice = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát?", "Xác nhận thoát", JOptionPane.OK_CANCEL_OPTION);
         	        if (choice == JOptionPane.OK_OPTION) {
         	            System.exit(0);
         	        }
         		}
         	}
         });
         btnThot.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnThot.setBackground(new Color(245, 255, 250));
         btnThot.setBounds(429, 620, 188, 39);
         panel.add(btnThot);
         
         comboBox = new JComboBox();
         
         comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         comboBox.setModel(new DefaultComboBoxModel(new String[] {"Thức ăn", "Đồ uống"}));
         comboBox.setBounds(346, 124, 166, 39);
         panel.add(comboBox);
         comboBox.addActionListener(this);
        
         comboBox.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		
          		
          		String selectedOption = comboBox.getSelectedItem().toString();
                if (selectedOption.equals("Thức ăn")) {
                	if(tableModel.getRowCount() >0) {
              			tableModel.setRowCount(0);
              		}
                        for (int i = 0; i < Food_Cache.FID.size(); i++) {
                            Object[] rowData = new Object[4];
                            rowData[0] = Food_Cache.FID.get(i);
                            rowData[1] = Food_Cache.FName.get(i);
                            rowData[2] = Food_Cache.FPrice.get(i);
                            rowData[3] = Food_Cache.FQuantity.get(i);
                            tableModel.addRow(rowData);
                        }
                } else if (selectedOption.equals("Đồ uống")) {  
                	if(tableModel.getRowCount() >0) {
              			tableModel.setRowCount(0);
              		}
                        for (int i = 0; i < Drink_Cache.Drink_ID.size(); i++) {
                            Object[] rowData = new Object[4];
                            rowData[0] = Drink_Cache.Drink_ID.get(i);
                            rowData[1] = Drink_Cache.Drink_Name.get(i);
                            rowData[2] = Drink_Cache.Drink_Price.get(i);
                            rowData[3] = Drink_Cache.Drink_Quantity.get(i);
                            tableModel.addRow(rowData);
                        }
                  
                }
          	}
          });
         mntmNhanVien.addActionListener(this);
    }
    public  CTC()  {
    	
    	GUI();
        
         
    }
    private void add_panel(JPanel panel) {
    	frame.getContentPane().removeAll(); 
   	 	frame.getContentPane().add(panel);
   	 	frame.revalidate(); 
   	 	frame.repaint();
    }
    public static void Set_tabelModel(Object[] rowData) {
    	tableModel.setRowCount(0);
    	tableModel.addRow(rowData);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Thực đơn":
            	if (frame.getContentPane().getComponent(0) != panel) {
            		frame.getContentPane().removeAll();
                    new CTC();
            	}
                break;
            case "Nhân viên":
                add_panel(dr);
                break;  
            case "Doanh thu":
                
                break;
            case "Xuất thống kê":
                
                break;
               
            case "Thoát":
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
