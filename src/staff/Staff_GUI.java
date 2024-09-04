package staff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import login.dangnhap1;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.event.MenuListener;

import about.DIALOG;

import javax.swing.event.MenuEvent;

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
//		mnNewMenu.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Custom-Icon-Design-Flatastic-5-Order-history.24.png")));
		menuBar.add(mnNewMenu);
		add_panel(gm);
		JMenuItem mntmNewMenuItem = new JMenuItem("Gọi món");
//		mntmNewMenuItem.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Icons8-Windows-8-Food-Waiter.24.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != gm) {
					add_panel(gm);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Thanh toán");
//		mntmNewMenuItem_1.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Erix-Subyarko-Medical-Invoice-Bill-Payment-Billing-Hospital.24.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != bill) {
					add_panel(bill);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Lựa chọn");
		mnNewMenu_1.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Dtafalonso-Android-Lollipop-Settings.24.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Đổi nhân viên");
//		mntmNewMenuItem_2.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Icons8-Windows-8-Users-Change-User.24.png")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new dangnhap1();
				
				////////////////
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Thoát");
//		mntmNewMenuItem_3.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-edit-delete.24.png")));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Trợ giúp");
		mnNewMenu_2.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				DIALOG dialog = new DIALOG("Hướng dẫn sử dụng", "hdsd_e.txt");
         		
        		 SwingUtilities.invokeLater(new Runnable() {

                    public void run() {

                        dialog.showDialog();

                    }

                });
			}
		});
//		mnNewMenu_2.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Icons-Land-Vista-People-Occupations-Technical-Support-Representative-Female-Light.24.png")));
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Về ứng dụng");
		mnNewMenu_3.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				DIALOG dialog = new DIALOG("Thông tin ứng dụng", "about_e.txt");
         		
        		 SwingUtilities.invokeLater(new Runnable() {

                    public void run() {

                        dialog.showDialog();

                    }

                });
			}
		});
//		mnNewMenu_3.setIcon(new ImageIcon(Staff_GUI.class.getResource("/image/Oxygen-Icons.org-Oxygen-Actions-help-about.24.png")));
		menuBar.add(mnNewMenu_3);
		

		
	}

}