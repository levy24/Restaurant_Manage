package staff;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class table_manage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(30, 30));
		
		Panel panel = new Panel();
		panel.setForeground(new Color(0, 0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 5, 20, 20));
		
		Panel panel_1 = new Panel();
		panel_1.setLayout(new BorderLayout(10, 10));
		Button button_b = new Button("BACK");
		JLabel label = new JLabel("TABLE MANAGE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		button_b.setBackground(Color.GRAY);
		button_b.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		label.setBackground(new Color(255, 255, 255));
		label.setFont(new Font("Time New Roman", Font.BOLD, 25));
		panel_1.add(label,"Center");
		panel_1.add(button_b,"West");
		
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		String btn[] = {"1","2","3","4","5",
						"6","7","8","9","10",
						"11","12","13","14","15",
						"16","17","18","19","20",
						"21","22","23","24","25"};
		for (int i=0;i<n;i++)
		{
			Button button = new Button(btn[i]);
			button.setFont(new Font("Times New Roman", Font.BOLD, 20));
			button.setBackground(Color.GREEN);
			panel.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (button.getBackground()==Color.RED)
					{
						JFrame frame = new JFrame();
						frame.setTitle("Status");
						frame.setBounds(550,300,400,200);
						frame.setLayout(new GridLayout(2,1,10,10));
						
						JLabel label = new JLabel("Xác nhận hủy chọn bàn này?");
						label.setFont(new Font("Times New Roman", Font.BOLD, 25));
						label.setHorizontalAlignment(SwingConstants.CENTER);
						frame.add(label);
						
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
								button.setBackground(Color.GREEN);
								frame.setVisible(false);
							}
						});
						
						panel.add(btn1);
						panel.add(btn2);
						
						frame.add(panel);
						
						frame.setVisible(true);
					}
					else {
						JFrame frame = new JFrame();
						frame.setTitle("Status");
						frame.setBounds(550,300,400,200);
						frame.setLayout(new GridLayout(2,1,10,10));
						
						JLabel label = new JLabel("Xác nhận chọn bàn này?");
						label.setFont(new Font("Times New Roman", Font.BOLD, 25));
						label.setHorizontalAlignment(SwingConstants.CENTER);
						frame.add(label);
						
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
								button.setBackground(Color.RED);
								frame.setVisible(false);
							}
						});
						
						panel.add(btn1);
						panel.add(btn2);
						
						frame.add(panel);
						
						frame.setVisible(true);
						
					}
				}
			});
		}
	}

}
