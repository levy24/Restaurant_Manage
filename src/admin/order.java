package admin;

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

import data_cache.Food_Cache;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class order extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public int kt;
	public boolean ktbtn[] = new boolean[25];
	public Button button[] = new Button[25];
	//public Button button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order frame = new order();
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
	public order() {
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chọn loại", "Đồ ăn", "Thức uống"}));
		comboBox.setBounds(200, 70, 150, 21);
		panel2.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Loại:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_3.setBounds(100, 70, 45, 20);
		panel2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Chọn món:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_4.setBounds(100, 125, 90, 20);
		panel2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Số lượng:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5.setBounds(100, 180, 70, 20);
		panel2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Số bàn:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_6.setBounds(100, 235, 70, 20);
		panel2.add(lblNewLabel_6);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Chọn món ăn"}));
		comboBox_1.setBounds(200, 125, 150, 21);
		panel2.add(comboBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		spinner.setBounds(200, 180, 50, 25);
		panel2.add(spinner);
		
		JTextPane textpane = new JTextPane();
		textpane.setBackground(new Color(255, 255, 255));
		textpane.setEditable(false);
		textpane.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textpane.setBounds(200, 235, 50, 25);
		panel2.add(textpane);
		
		Object[][] data = new Object[5][4];
		for (int i = 0; i < Food_Cache.FID.size(); i++) {
		    data[i][0] = Food_Cache.FID.get(i); 
		    data[i][1] = Food_Cache.FName.get(i); 
		    data[i][2] = Food_Cache.FPrice.get(i); 
		    data[i][3] = Food_Cache.FQuantity.get(i);
		    
		}

        String[] columnNames = {"ID", "Tên món", "Giá (VND)","Số lượng"};
        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 320, 800, 250);
		panel2.add(scrollPane);
		
		JButton btnNewButton = new JButton("Chọn món");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton.setBounds(570, 150, 150, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Thêm món ăn vào danh sách order
			}
		});
		panel2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton_1.setBounds(570, 70, 150, 40);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Sửa danh sách món đã order
			}
		});
		panel2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Chốt đơn");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton_2.setBounds(570, 225, 150, 40);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Chốt đơn order
				int k = Integer.parseInt(textpane.getText());
				ktbtn[k-1]=false;
				button[k-1].setBackground(re);
				textpane.setText("");
			}
		});
		panel2.add(btnNewButton_2);
		
		JLabel lblNewLabel_7 = new JLabel("Tổng tiền:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(183, 600, 100, 25);
		panel2.add(lblNewLabel_7);
		
		JTextPane textPane = new JTextPane();
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
