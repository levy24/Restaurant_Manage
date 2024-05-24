package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import connectDTB.connect;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;
import java.awt.event.ItemListener;
import java.net.ConnectException;
import java.awt.event.ItemEvent;
import javax.swing.SpinnerListModel;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;


public class CTC extends JFrame implements ActionListener {

    public static JFrame frame = new JFrame();;
    private static JMenuBar menuBar;
    private static JPanel panel;
    static DefaultTableModel tableModel;
    
   
    private JTable tbFood;
    private JTextField txtTim;
    private JTextField txtTen;
    private JTextField txtGia;
    private static JComboBox comboBox;
    public employee ep; 
    public statistics sttc;
    
    public void GUI() {
//    	 frame.setIconImage(Toolkit.getDefaultToolkit().getImage(CTC.class.getResource("/image/Iconarchive-Essential-Buildings-Restaurant.ico")));
    	 frame.setTitle("ADMIN");
         frame.setBounds(0, 0, 1900, 1000);
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         menuBar = new JMenuBar();
         frame.setJMenuBar(menuBar);

         JMenu mnQuanLy = new JMenu("Quản lý");
//         mnQuanLy.setIcon(new ImageIcon(CTC.class.getResource("/image/Papirus-Team-Papirus-Apps-System-file-manager.24.png")));
         menuBar.add(mnQuanLy);

         JMenuItem mntmMonAn = new JMenuItem("Thực đơn");
//         mntmMonAn.setIcon(new ImageIcon(CTC.class.getResource("/image/Jamespeng-Cuisine-Pork-Chop-Set.24.png")));
         mnQuanLy.add(mntmMonAn);
         mntmMonAn.addActionListener(this);

         JMenuItem mntmNhanVien = new JMenuItem("Nhân viên");
//         mntmNhanVien.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Sleek-Xp-Basic-Preppy.24.png")));
         mnQuanLy.add(mntmNhanVien);
         
         JMenu mnNewMenu = new JMenu("Thống kê");
//         mnNewMenu.setIcon(new ImageIcon(CTC.class.getResource("/image/Awicons-Vista-Artistic-Chart.24.png")));
         menuBar.add(mnNewMenu);
         
         JMenuItem mntmDoanhthu = new JMenuItem("Doanh thu");
         mntmDoanhthu.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		
         		if(frame.getContentPane().getComponent(0) != sttc) {
         			add_panel(sttc);
         		}
         	}
         });
         
//         mntmDoanhthu.setIcon(new ImageIcon(CTC.class.getResource("/image/Designcontest-Ecommerce-Business-Dollar.24.png")));
         mnNewMenu.add(mntmDoanhthu);
         
         JMenuItem mntmXuatThongKe = new JMenuItem("Món ăn");
         mnNewMenu.add(mntmXuatThongKe);
         
         JMenu mnTuyChon = new JMenu("Tùy chọn");
//         mnTuyChon.setIcon(new ImageIcon(CTC.class.getResource("/image/Dtafalonso-Android-Lollipop-Settings.24.png")));
         menuBar.add(mnTuyChon);
         
         JMenuItem mntmThoat = new JMenuItem("Thoát");
         mnTuyChon.add(mntmThoat);
         mntmThoat.addActionListener(this);
        frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
         
        panel = new JPanel();
        panel.setBackground(new Color(255, 222, 173));
        
         frame.getContentPane().add(panel);
         panel.setLayout(null);
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Mã món");
         tableModel.addColumn("Tên món");
         tableModel.addColumn("Giá thành (VND)");
         tableModel.addColumn("Trạng thái phục vụ");
         tbFood = new JTable();
         tbFood.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
         tbFood.setModel(tableModel);
         
         tbFood.setSurrendersFocusOnKeystroke(true);
         tbFood.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         
         tbFood.setCellSelectionEnabled(true);
         tbFood.setColumnSelectionAllowed(true);
         tbFood.setFillsViewportHeight(true);
         tbFood.setBackground(Color.WHITE);
         tbFood.setBounds(800, 50, 600,400);
         
         tbFood.getColumnModel().getColumn(0).setPreferredWidth(40);
         tbFood.getColumnModel().getColumn(1).setPreferredWidth(269);
         tbFood.getColumnModel().getColumn(2).setPreferredWidth(150);
         tbFood.getColumnModel().getColumn(2).setPreferredWidth(50);
         JScrollPane scrollPane = new JScrollPane(tbFood);
         scrollPane.setSize(700, 500);
         scrollPane.setLocation(805, 229);
         panel.add(scrollPane);
         try {
			RowTable("1");
		} catch (SQLException e1) {
			
		}
         
         JLabel lblNewLabel = new JLabel("Thực đơn nhà hàng");
         lblNewLabel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(105, 105, 105)));
         lblNewLabel.setForeground(new Color(0, 0, 139));
         lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
         lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel.setBounds(255, 238, 359, 53);
         panel.add(lblNewLabel);
         
         JPanel panel_1 = new JPanel();
         panel_1.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
         panel_1.setBounds(95, 388, 419, 53);
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
         
         JPanel panel_1_2 = new JPanel();
         panel_1_2.setBorder(new LineBorder(new Color(0, 0, 139), 2, true));
         panel_1_2.setBounds(95, 451, 419, 53);
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
         panel_1_3.setBorder(new LineBorder(new Color(0, 0, 139), 2, true));
         panel_1_3.setBounds(95, 514, 419, 53);
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
         panel_1_4.setBorder(new LineBorder(new Color(0, 0, 139), 2, true));
         panel_1_4.setBounds(95, 577, 419, 53);
         panel.add(panel_1_4);
         panel_1_4.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
         JLabel lblNewLabel_1_4 = new JLabel("Trạng thái");
         lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         panel_1_4.add(lblNewLabel_1_4);
         
         JSpinner spinner = new JSpinner();
         spinner.setModel(new SpinnerListModel(new String[] {"On", "Off"}));
         spinner.setPreferredSize(new Dimension(60, 30));
         spinner.setFont(new Font("Times New Roman", Font.BOLD, 20));
         panel_1_4.add(spinner);
         
         
         tbFood.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {        
                    int selected = tbFood.getSelectedRow();
                    if(selected != -1) {
                    	Object value_Name = tbFood.getValueAt(selected, 1); 
                        Object value_Price = tbFood.getValueAt(selected, 2); 
                        Object value_On = tbFood.getValueAt(selected, 3); 
             			txtTen.setText(value_Name.toString()) ; 
             			txtGia.setText(value_Price.toString());
             			spinner.setValue(value_On);
                    }
                    
                }
				
			}
		});
         JButton btnNewButton = new JButton("Thêm món");
//         btnNewButton.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Button-Button-Add.16.png")));
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         	
                String tenMon = txtTen.getText().trim();
                String giaTienStr = txtGia.getText().trim();
        
                if (tenMon.isEmpty() || giaTienStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int giaTien;
                try {
                    giaTien = Integer.parseInt(giaTienStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Giá tiền phải là một số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
               
                String xborString = spinner.getValue().toString();
                try {
					Chuc_Nang.Them_mon(tenMon,giaTien,comboBox.getSelectedIndex()+1,xborString);
				} catch (SQLException e1) {
					
				}
                txtTen.setText("");
                txtGia.setText("");
                

                JOptionPane.showMessageDialog(null, "Thêm món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                try {
					Refresh_Table();
				} catch (SQLException e1) {
					
				}
            }
         	
         });
         btnNewButton.setBackground(new Color(124, 252, 0));
         btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnNewButton.setBounds(596, 465, 145, 39);
         panel.add(btnNewButton);
         
         
  		 
         JButton btnXaMn = new JButton("Xóa món");
         btnXaMn.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         			int selected = tbFood.getSelectedRow();
         			if( selected != -1) {
         				Object value = tbFood.getValueAt(selected, 1); 
             			Object valueID = tbFood.getValueAt(selected, 0);
             			String textString = value.toString(); 
             			int id = (int)valueID;
             		    try {
    						Chuc_Nang.XoaMon(textString,id);
    						Refresh_Table();
    					} catch (SQLException e1) {
    					}
         			} else {
         				JOptionPane.showMessageDialog(null, "Chọn một món trong danh sach", "Lời nhắc", JOptionPane.INFORMATION_MESSAGE);
         			}
         			
         		 

         	}
         });
//         btnXaMn.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Button-Button-Delete.16.png")));
         btnXaMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnXaMn.setBackground(new Color(255, 0, 0));
         btnXaMn.setBounds(596, 528, 145, 39);
         panel.add(btnXaMn);
         
         JButton btnSaMn = new JButton("Sửa món");
         btnSaMn.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		int selected = tbFood.getSelectedRow();   
         		if(selected >= 0) {
         			Object valueID = tbFood.getValueAt(selected, 0);
         			int id = (int)valueID;
         			
         			try {
    					Chuc_Nang.Cap_nhat(id,txtTen.getText(), Integer.parseInt(txtGia.getText()), spinner.getValue().toString());
    					JOptionPane.showMessageDialog(null, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    					Refresh_Table();
    				} catch (NumberFormatException e1) {
    					
    				} catch (SQLException e1) {
    					
    				}
         		} else {
         			JOptionPane.showMessageDialog(null, "Phải chọn một món trong danh sách", "Lời nhắc", JOptionPane.INFORMATION_MESSAGE);
         		}
         		
     			
     			
     			
     			
         	}
         });
//         btnSaMn.setIcon(new ImageIcon(CTC.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png")));
         btnSaMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnSaMn.setBackground(new Color(255, 215, 0));
         btnSaMn.setBounds(596, 591, 145, 39);
         panel.add(btnSaMn);
         
         JButton btnTmKim = new JButton("   Tìm");
//         btnTmKim.setIcon(new ImageIcon(CTC.class.getResource("/image/Ampeross-Qetto-2-Search.32.png")));
         btnTmKim.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		 
         			int selected = comboBox.getSelectedIndex() + 1;
         			try {
						Chuc_Nang.Tim_kiem(txtTim.getText(), selected);
					} catch (SQLException e1) {
						
					}
         		
         	}
         });
         btnTmKim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnTmKim.setBackground(new Color(245, 255, 250));
         btnTmKim.setBounds(596, 388, 145, 39);
         panel.add(btnTmKim);
         
         JButton btntLi = new JButton("Đặt lại");
         btntLi.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		if(e.getSource() == btntLi) {
         			txtTim.setText("");
         			txtTen.setText("");
         			txtGia.setText("");
         			
         		}
         	}
         });
         btntLi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btntLi.setBackground(new Color(245, 255, 250));
         btntLi.setBounds(350, 690, 137, 39);
         panel.add(btntLi);
         
         comboBox = new JComboBox();
         comboBox.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
         
         comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khai vị", "Món chính", "Tráng miệng", "Đồ uống"}));
         comboBox.setBounds(303, 326, 166, 39);
         panel.add(comboBox);
         
         JButton btnLu = new JButton("Lưu");
         btnLu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
         btnLu.setBackground(new Color(245, 255, 250));
         btnLu.setBounds(129, 690, 156, 39);
         panel.add(btnLu);
         
         JLabel lblNewLabel_4 = new JLabel("Danh mục");
         lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         lblNewLabel_4.setBounds(175, 333, 103, 24);
         panel.add(lblNewLabel_4);
         
         JPanel panel_2 = new JPanel();
         panel_2.setBackground(new Color(255, 99, 71));
         panel_2.setBounds(0, 0, 1540, 126);
         panel.add(panel_2);
         panel_2.setLayout(null);
         
         JLabel lblNewLabel_2 = new JLabel("");
         lblNewLabel_2.setBounds(10, 0, 137, 128);
         panel_2.add(lblNewLabel_2);
//         lblNewLabel_2.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Scrap-Administrator.128.png")));
         
         JLabel lblNewLabel_5 = new JLabel("Quản lý");
         lblNewLabel_5.setBounds(174, 31, 243, 71);
         panel_2.add(lblNewLabel_5);
         lblNewLabel_5.setFont(new Font("Segoe UI Black", Font.PLAIN, 60));
         
         JPanel panel_3 = new JPanel();
         panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
         panel_3.setForeground(new Color(0, 0, 0));
         panel_3.setBackground(new Color(218, 165, 32));
         panel_3.setBounds(805, 146, 700, 83);
         panel.add(panel_3);
         
         JLabel lblDanhSchMn = new JLabel("Danh sách món");
         panel_3.add(lblDanhSchMn);
         lblDanhSchMn.setForeground(new Color(105, 105, 105));
         lblDanhSchMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
         
         JLabel lblNewLabel_3 = new JLabel("");
         panel_3.add(lblNewLabel_3);
//         lblNewLabel_3.setIcon(new ImageIcon(CTC.class.getResource("/image/Jamespeng-Cuisine-Pork-Chop-Set.64.png")));
         comboBox.addActionListener(this);
        
         comboBox.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		int selectedIndex = comboBox.getSelectedIndex() + 1;
          		String Selected  = Integer.toString(selectedIndex);
          		
          		try {
					RowTable(Selected);
				} catch (SQLException e1) {
				}
                
          	}
          });
         
        mntmMonAn.addActionListener(this);
        mntmNhanVien.addActionListener(this);
        
         
    }
    public  CTC()   {
    	
    	GUI();
         
    }
    private void add_panel(JPanel panel) {
    	frame.getContentPane().removeAll(); 
   	 	frame.getContentPane().add(panel);
   	 	frame.revalidate(); 
   	 	frame.repaint();
    }
    private static void Refresh_Table() throws SQLException {
    	String selectedValue = comboBox.getSelectedItem().toString();
    	RowTable(selectedValue);
    	
    }
    private static void  RowTable(String x) throws SQLException {
    	connect connector = new connect();
    	tableModel.setRowCount(0);
        Connection conn = (Connection) connector.connection;
		if( conn != null) {
			Statement stmt = conn.createStatement();
        	String sql = "SELECT * FROM food_drink WHERE Classify = " + x;
        	ResultSet resultSet = stmt.executeQuery(sql);
        	Object[] Data = new Object[4];
        	while (resultSet.next()) {
        		int id = resultSet.getInt("ID");
        		String name = resultSet.getString("Name");
        		int price= resultSet.getInt("Price");
        		int status = resultSet.getInt("Status");
        		 Data[0] = id;
        		 Data[1] = name;
        		 Data[2] = price;
        		 Data[3] = ( status == 0) ? "Off" : "On";
        		 tableModel.addRow(Data);  
        		 
        		 }	
        resultSet.close();
        stmt.close();
        if(conn != null) {
        	conn.close();
        }
		}
            
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Thực đơn":
            	if (frame.getContentPane().getComponent(0) != panel) {
                    add_panel(panel);
            	}
                break;
            case "Nhân viên": 	
			
				if (frame.getContentPane().getComponent(0) != ep) {
            		add_panel(ep);
				}    
                break;  
               
            case "Thoát":
                System.exit(0);
                break;
            default:
                break;
        }
    }
}