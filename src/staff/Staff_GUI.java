package staff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.dangnhap1;
import login.start;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Staff_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel gm = new Goimon();
	private JPanel bill = new Bill();
	/**
	 * Launch the application.
	 */
	
	private void add_panel(JPanel panel) {
    	getContentPane().removeAll(); 
   	 	getContentPane().add(panel);
   	 	revalidate(); 
   	 	repaint();
    }
	/**
	 * Create the frame.
	 */
	public Staff_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1000);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Đặt món");
		menuBar.add(mnNewMenu);
		add_panel(gm);
		JMenuItem mntmNewMenuItem = new JMenuItem("Gọi món");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != gm) {
					add_panel(gm);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Thanh toán");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != bill) {
					add_panel(bill);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Lựa chọn");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Đổi nhân viên");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new dangnhap1(false);
				
				////////////////
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Thoát");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		

		
	}

}
