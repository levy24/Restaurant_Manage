package admin;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import about.DIALOG;
import connectDTB.connect;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;
import font.RoundedBorder;
import login.dangnhap1;
import picocli.CommandLine.Help;

import java.awt.event.ItemListener;
import java.net.ConnectException;
import java.awt.event.ItemEvent;
import javax.swing.SpinnerListModel;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;


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
    public best_seller seller; 
    private JTextField txtDV;
    public void GUI() {
    	 frame.setIconImage(Toolkit.getDefaultToolkit().getImage(CTC.class.getResource("/image/Iconarchive-Essential-Buildings-Restaurant.ico")));
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
         mntmDoanhthu.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		
         		if(frame.getContentPane().getComponent(0) != sttc) {
         			add_panel(sttc);
         		}
         	}
         });
         
         mntmDoanhthu.setIcon(new ImageIcon(CTC.class.getResource("/image/Designcontest-Ecommerce-Business-Dollar.24.png")));
         mnNewMenu.add(mntmDoanhthu);
         
         JMenuItem mntmXuatThongKe = new JMenuItem("Món ăn");
         mntmXuatThongKe.setIcon(new ImageIcon(CTC.class.getResource("/image/Jamespeng-Cuisine-Pork-Chop-Set.24.png")));
         mntmXuatThongKe.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		if( frame.getContentPane().getComponent(0) != seller) {
         			add_panel(seller);
         		}
         	}
         });
         mnNewMenu.add(mntmXuatThongKe);
         
         JMenu mnTuyChon = new JMenu("Tùy chọn");
         mnTuyChon.setIcon(new ImageIcon(CTC.class.getResource("/image/Dtafalonso-Android-Lollipop-Settings.24.png")));
         menuBar.add(mnTuyChon);
         
         JMenuItem mntmThoat = new JMenuItem("Thoát");
         mntmThoat.setIcon(new ImageIcon(CTC.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-edit-delete.24.png")));
         mntmThoat.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		System.exit(0);
         	}
         });
         mnTuyChon.add(mntmThoat);
         
         JMenu mnNewMenu_1 = new JMenu("Trợ giúp");
         mnNewMenu_1.addMenuListener(new MenuListener() {
         	public void menuCanceled(MenuEvent e) {
         	}
         	public void menuDeselected(MenuEvent e) {
         	}
         	public void menuSelected(MenuEvent e) {
         		DIALOG dialog = new DIALOG("Hướng dẫn sử dụng", "hdsd_ad.txt");
         		
         		 SwingUtilities.invokeLater(new Runnable() {

                     public void run() {

                         dialog.showDialog();

                     }

                 });
         	}
         });
         
         mnNewMenu_1.setIcon(new ImageIcon(CTC.class.getResource("/image/Icons-Land-Vista-People-Occupations-Technical-Support-Representative-Female-Light.24.png")));
         menuBar.add(mnNewMenu_1);
         
         JMenu mnNewMenu_2 = new JMenu("Về ứng dụng");
         mnNewMenu_2.addMenuListener(new MenuListener() {
         	public void menuCanceled(MenuEvent e) {
         	}
         	public void menuDeselected(MenuEvent e) {
         	}
         	public void menuSelected(MenuEvent e) {
         		DIALOG dialog = new DIALOG("Thông tin ứng dụng", "about_ad.txt");
         		SwingUtilities.invokeLater(new Runnable() {

                    public void run() {

                        dialog.showDialog();

                    }

                });
         	}
         });
         mnNewMenu_2.setIcon(new ImageIcon(CTC.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-help-about.24.png")));
         menuBar.add(mnNewMenu_2);
         
        frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
         
        panel = new JPanel();
        panel.setForeground(new Color(255, 240, 245));
        panel.setBackground(new Color(250, 240, 230));
        
         frame.getContentPane().add(panel);
         panel.setLayout(null);
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Mã món");
         tableModel.addColumn("Tên món");
         tableModel.addColumn("Giá thành (VND)");
         tableModel.addColumn("Đơn vị tính");
         tableModel.addColumn("Trạng thái phục vụ");
         
         tbFood = new JTable();
         tbFood.setModel(tableModel);
         
         tbFood.setSurrendersFocusOnKeystroke(true);
         tbFood.setFont(new Font("Times New Roman", Font.PLAIN, 15));
         
         tbFood.setCellSelectionEnabled(true);
         tbFood.setColumnSelectionAllowed(true);
         tbFood.setFillsViewportHeight(true);
         tbFood.setBackground(Color.WHITE);
         tbFood.setBounds(800, 50, 600,320);
         
         tbFood.getColumnModel().getColumn(0).setPreferredWidth(30);
         tbFood.getColumnModel().getColumn(1).setPreferredWidth(250);
         tbFood.getColumnModel().getColumn(2).setPreferredWidth(130);
         tbFood.getColumnModel().getColumn(3).setPreferredWidth(50);
         tbFood.getColumnModel().getColumn(4).setPreferredWidth(50);
         JScrollPane scrollPane = new JScrollPane(tbFood);
         scrollPane.setSize(700, 620);
         scrollPane.setLocation(40, 120);
         panel.add(scrollPane);
         try {
			RowTable("1");
		} catch (SQLException e1) {
			
		}
         
         JLabel lblNewLabel = new JLabel("Quản lý thực đơn");
         lblNewLabel.setBorder(null);
         lblNewLabel.setForeground(new Color(128, 128, 128));
         lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
         lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel.setBounds(976, 58, 359, 53);
         panel.add(lblNewLabel);
         
         JPanel panel_1_4 = new JPanel();
         panel_1_4.setBackground(new Color(255, 255, 255));
         panel_1_4.setBounds(1190, 507, 306, 46);
         panel.add(panel_1_4);
         panel_1_4.setLayout(null);
         
         JLabel lblNewLabel_1_4 = new JLabel("Trạng thái");
         lblNewLabel_1_4.setForeground(new Color(128, 128, 128));
         lblNewLabel_1_4.setBounds(26, 12, 90, 24);
         lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         panel_1_4.add(lblNewLabel_1_4);
         
         JSpinner spinner = new JSpinner();
         spinner.setForeground(new Color(128, 128, 128));
         spinner.setBounds(177, 9, 60, 30);
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
                        Object value_On = tbFood.getValueAt(selected, 4); 
                        Object value_DV = tbFood.getValueAt(selected, 3);
             			txtTen.setText(value_Name.toString()) ; 
             			txtGia.setText(value_Price.toString());
             			txtDV.setText(value_DV.toString());
             			spinner.setValue(value_On);
                    }
                    
                }
				
			}
		});
         JButton btnNewButton = new JButton("Thêm món");
         btnNewButton.setForeground(new Color(128, 128, 128));
         btnNewButton.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Button-Button-Add.16.png")));
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         	
                String tenMon = txtTen.getText().trim();
                String giaTienStr = txtGia.getText().trim();
                String donvi = txtDV.getText().trim();
                
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
					Chuc_Nang.Them_mon(tenMon,giaTien,comboBox.getSelectedIndex()+1,xborString, donvi);
				} catch (SQLException e1) {
					
				}
                txtTen.setText("");
                txtGia.setText("");
                txtDV.setText("");

                JOptionPane.showMessageDialog(null, "Thêm món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                try {
					Refresh_Table();
				} catch (SQLException e1) {
					
				}
            }
         	
         });
         btnNewButton.setBackground(new Color(255, 255, 255));
         btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
         btnNewButton.setBounds(1002, 701, 156, 39);
         panel.add(btnNewButton);
         
         
  		 
         JButton btnXaMn = new JButton("Xóa món");
         btnXaMn.setForeground(new Color(128, 128, 128));
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
         btnXaMn.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Button-Button-Delete.16.png")));
         btnXaMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
         btnXaMn.setBackground(new Color(255, 255, 255));
         btnXaMn.setBounds(1190, 701, 145, 39);
         panel.add(btnXaMn);
         
         JButton btnSaMn = new JButton("Sửa món");
         btnSaMn.setForeground(new Color(128, 128, 128));
         btnSaMn.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		int selected = tbFood.getSelectedRow();   
         		if(selected >= 0) {
         			Object valueID = tbFood.getValueAt(selected, 0);
         			int id = (int)valueID;
         			
         			try {
    					Chuc_Nang.Cap_nhat(id,txtTen.getText(), Integer.parseInt(txtGia.getText()), spinner.getValue().toString(), txtDV.getText());
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
         btnSaMn.setIcon(new ImageIcon(CTC.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png")));
         btnSaMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
         btnSaMn.setBackground(new Color(255, 255, 255));
         btnSaMn.setBounds(1351, 701, 145, 39);
         panel.add(btnSaMn);
         
         JButton btnTmKim = new JButton("  Tìm");
         btnTmKim.setForeground(new Color(128, 128, 128));
         btnTmKim.setBorder(new RoundedBorder(22));
         btnTmKim.setIcon(new ImageIcon(CTC.class.getResource("/image/Ampeross-Qetto-2-Search.32.png")));
         btnTmKim.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		 
         			int selected = comboBox.getSelectedIndex() + 1;
         			try {
						Chuc_Nang.Tim_kiem(txtTim.getText(), selected);
					} catch (SQLException e1) {
						
					}
         		
         	}
         });
         btnTmKim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
         btnTmKim.setBackground(new Color(255, 255, 255));
         btnTmKim.setBounds(1190, 316, 130, 47);
         panel.add(btnTmKim);
         
         JButton btntLi = new JButton("Đặt lại");
         btntLi.setForeground(new Color(128, 128, 128));
         btntLi.setBorder(new RoundedBorder(20));
         btnNewButton.setBorder(new RoundedBorder(20));
         btnSaMn.setBorder(new RoundedBorder(20));
         btnXaMn.setBorder(new RoundedBorder(20));
         btntLi.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		 
         		txtTim.setText("");
         		txtTen.setText("");
         		txtGia.setText("");
         		txtDV.setText("");
         		try {
					Refresh_Table();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         	}
         });
         btntLi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
         btntLi.setBackground(new Color(255, 255, 255));
         btntLi.setBounds(815, 701, 164, 39);
         panel.add(btntLi);
         
         comboBox = new JComboBox();
         comboBox.setForeground(new Color(128, 128, 128));
         
         comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khai vị", "Món chính", "Tráng miệng", "Đồ uống"}));
         comboBox.setBounds(944, 209, 214, 39);
         panel.add(comboBox);
         
         JLabel lblNewLabel_4 = new JLabel("Danh mục");
         lblNewLabel_4.setForeground(new Color(128, 128, 128));
         lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         lblNewLabel_4.setBounds(819, 216, 103, 24);
         panel.add(lblNewLabel_4);
         
         JPanel panel_3 = new JPanel();
         panel_3.setForeground(new Color(0, 0, 0));
         panel_3.setBackground(new Color(250, 240, 230));
         panel_3.setBounds(164, 57, 359, 53);
         panel.add(panel_3);
         panel_3.setLayout(null);
         
         JLabel lblDanhSchMn = new JLabel("Danh sách món");
         lblDanhSchMn.setBounds(10, 5, 264, 47);
         panel_3.add(lblDanhSchMn);
         lblDanhSchMn.setForeground(new Color(105, 105, 105));
         lblDanhSchMn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
         
         JLabel lblNewLabel_3 = new JLabel("");
         lblNewLabel_3.setBounds(287, 0, 55, 54);
         panel_3.add(lblNewLabel_3);
         lblNewLabel_3.setIcon(new ImageIcon(CTC.class.getResource("/image/Jamespeng-Cuisine-Pork-Chop-Set.64.png")));
         
         JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
         lblNewLabel_1.setForeground(new Color(128, 128, 128));
         lblNewLabel_1.setBounds(815, 288, 79, 24);
         panel.add(lblNewLabel_1);
         lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
         
         txtTim = new JTextField();
         txtTim.setBorder(new LineBorder(Color.WHITE, 4, true));
         txtTim.setBounds(815, 317, 343, 46);
        
         panel.add(txtTim);
         txtTim.setFont(new Font("Times New Roman", Font.PLAIN, 17));
         txtTim.setColumns(20);
         
         JLabel lblNewLabel_1_2 = new JLabel("Tên món");
         lblNewLabel_1_2.setForeground(new Color(128, 128, 128));
         lblNewLabel_1_2.setBounds(815, 383, 79, 24);
         panel.add(lblNewLabel_1_2);
         lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         
         txtTen = new JTextField();
         txtTen.setBounds(815, 407, 343, 46);
         panel.add(txtTen);
         txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 17));
         txtTen.setColumns(20);
         
         txtGia = new JTextField();
         txtGia.setBounds(1190, 407, 306, 46);
         panel.add(txtGia);
         txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
         txtGia.setColumns(20);
         
         JLabel lblNewLabel_1_3 = new JLabel("Giá thành");
         lblNewLabel_1_3.setForeground(new Color(128, 128, 128));
         lblNewLabel_1_3.setBounds(1190, 383, 85, 24);
         panel.add(lblNewLabel_1_3);
         lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         
         JLabel lblNewLabel_2 = new JLabel("");
         lblNewLabel_2.setBounds(1360, 0, 48, 67);
         panel.add(lblNewLabel_2);
         lblNewLabel_2.setIcon(new ImageIcon(CTC.class.getResource("/image/Hopstarter-Sleek-Xp-Basic-Preppy.48.png")));
         
         JLabel lblNewLabel_5 = new JLabel("Quản lý");
         lblNewLabel_5.setForeground(new Color(128, 128, 128));
         lblNewLabel_5.setBounds(1411, 10, 119, 53);
         panel.add(lblNewLabel_5);
         lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
         
         JLabel lblNewLabel_1_3_1 = new JLabel("Đơn vị tính");
         lblNewLabel_1_3_1.setForeground(new Color(128, 128, 128));
         lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
         lblNewLabel_1_3_1.setBounds(810, 486, 112, 24);
         panel.add(lblNewLabel_1_3_1);
         
         txtDV = new JTextField();
         txtDV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
         txtDV.setColumns(20);
         txtDV.setBounds(815, 507, 343, 46);
         panel.add(txtDV);
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
    	String selectedValue = Integer.toString(comboBox.getSelectedIndex()+1);
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
        	Object[] Data = new Object[5];
        	while (resultSet.next()) {
        		int id = resultSet.getInt("ID");
        		String name = resultSet.getString("Name");
        		int price= resultSet.getInt("Price");
        		int status = resultSet.getInt("Status");
        		String dvString = resultSet.getString("DonVi");
        		 Data[0] = id;
        		 Data[1] = name;
        		 Data[2] = price;
        		 Data[3] = dvString;
        		 Data[4] = ( status == 0) ? "Off" : "On";
        		 
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
          
            default:
                break;
        }
    }
}