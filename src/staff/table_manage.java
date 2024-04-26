package staff;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data_cache.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.foreign.ValueLayout.OfBoolean;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class table_manage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public int kt;
	public boolean ktbtn[] = new boolean[25];
	public Button button[] = new Button[25];
	boolean isFoodSelected;
	long total = 0;
	JTextPane textPane;
	//public Button button;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table_manage frame = new table_manage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public table_manage() {
		int n = 25;
		Color gr = new Color(0,255,128);
		Color re = new Color(220,20,60);
		Color ye = new Color(255, 255, 128);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for (int i=0;i<n;i++)
			ktbtn[i] = true;
		
		JLabel lblNewLabel = new JLabel("ORDER");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1550, 50);
		contentPane.add(lblNewLabel);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Panel chon mon an
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(128, 128, 192));
		panel2.setBounds(700, 100, 825, 650);	
		contentPane.add(panel2);
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
			     if(id != -1 && price != -1) {
				     Object[] row = new Object[4];
				     row[0] = id; 
				     row[1] = selectedItem;
				     row[2] = price; 
				     row[3] = quantity;
				     model.addRow(row);
				     total += price * quantity;
			     }
			     else {
			    	 JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin về món ăn " + selectedItem);
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
				//Sửa danh sách món đã order
			}
		});
		panel2.add(btnSua);
		
		JButton btnChotDon = new JButton("Chốt đơn");
		btnChotDon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnChotDon.setBounds(570, 225, 150, 40);
		btnChotDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Chốt đơn order
				int k = Integer.parseInt(textpane.getText());
				ktbtn[k-1]=false;
				button[k-1].setBackground(re);
				textpane.setText("");
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
		contentPane.add(lblNewLabel_2);
		
		//Panel chọn bàn
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(10, 100, 650, 650);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(5, 5, 10, 10));
		
		JLabel lblNewLabel_1 = new JLabel("BÀN ĂN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(273, 58, 128, 40);
		contentPane.add(lblNewLabel_1);
		
		String btn[] = {"1","2","3","4","5",
				"6","7","8","9","10",
				"11","12","13","14","15",
				"16","17","18","19","20",
				"21","22","23","24","25"};
		
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		for (int i=0; i<n; i++)
		{
			button[i] = new Button(btn[i]);
			button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
			button[i] = new Button(btn[i]);
			button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
			
			button[i].setBackground(gr);
			panel.add(button[i]);
			kt = i;
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int kt = Integer.parseInt(e.getActionCommand()) - 1;
					if (button[kt].getBackground()==gr)
					{
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
							public void actionPerformed(ActionEvent h)
							{
								frame.setVisible(false);
							}
						});
						
						btn2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent h)
							{
								textpane.setText(e.getActionCommand());
								button[kt].setBackground(ye);
								for (int j=0;j<n;j++)
									if (button[j].getBackground()==ye && j!=kt)
									{
										button[j].setBackground(gr);
										break;
									}
								frame.setVisible(false);
							}
						});
						
						panel.add(btn1);
						panel.add(btn2);
						
						frame.getContentPane().add(panel);
						
						frame.setVisible(true);
						
					} else if (!ktbtn[Integer.parseInt(e.getActionCommand())-1]){
						JOptionPane.showMessageDialog(null,"Bàn này đang có khách!!!");
					}
				}
			});
		}
	}
}