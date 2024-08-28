package login;

import java.awt.EventQueue;
import javax.swing.*;

import admin.CTC;
import admin.best_seller;
import admin.employee;
import admin.statistics;

import java.awt.*;
import java.sql.*;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import admin.CTC;

//import admin.employee;
//import admin.statistics;
import connectDTB.connect;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;
import security.Crypt;
import staff.Staff_GUI;
import font.RoundedBorder;
import font.borderRadius;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class dangnhap1 extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField password;
    private boolean isAdmin = true;
    public static String loggedInUserID = "101200";

    public dangnhap1() {
       
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        
        frame.setTitle("Restaurant Login");
        frame.setBounds(0, 0, 750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(dangnhap1.class.getResource("/image/logo363.png")));
        lblNewLabel.setBounds(0, 0, 411, 363);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("Đăng nhập");
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.setBorder(new RoundedBorder(20));
        btnNewButton.addActionListener(this); 
        btnNewButton.setForeground(new Color(165, 42, 42));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton.setBounds(577, 262, 128, 33);
        frame.getContentPane().add(btnNewButton);
        
                textField = new JTextField();
                textField.setBackground(UIManager.getColor("Button.light"));
                textField.setBounds(440, 121, 268, 36);
                frame.getContentPane().add(textField);
                textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
                textField.setColumns(15);
        
        JLabel lblNewLabel_4 = new JLabel("ĐĂNG NHẬP");
        lblNewLabel_4.setForeground(Color.GRAY);
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(463, 43, 221, 50);
        frame.getContentPane().add(lblNewLabel_4);
                
                        JLabel lbTK = new JLabel("Tài khoản");
                        lbTK.setBounds(436, 97, 86, 24);
                        frame.getContentPane().add(lbTK);
                        lbTK.setForeground(new Color(165, 42, 42));
                        lbTK.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        
                                password = new JPasswordField ();
                                password.setBackground(UIManager.getColor("Button.light"));
                                password.setBounds(440, 189, 268, 35);
                                frame.getContentPane().add(password);
                                password.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                password.setColumns(15);
                
                        JLabel lbMK = new JLabel("Mật khẩu");
                        lbMK.setBounds(435, 167, 92, 24);
                        frame.getContentPane().add(lbMK);
                        lbMK.setForeground(new Color(165, 42, 42));
                        lbMK.setFont(new Font("Times New Roman", Font.BOLD, 20));
                
                JButton btnCancel = new JButton("Thoát");
                btnCancel.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		System.exit(0);
                	}
                });
                btnCancel.setBorder(new RoundedBorder(20));
                btnCancel.setBackground(new Color(192, 192, 192));
                btnCancel.setForeground(new Color(165, 42, 42));
                btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 16));
                btnCancel.setBounds(440, 262, 122, 33);
                frame.getContentPane().add(btnCancel);
                
                JLabel lblNewLabel_1 = new JLabel("New label");
                lblNewLabel_1.setIcon(new ImageIcon(dangnhap1.class.getResource("/image/login.png")));
                lblNewLabel_1.setBounds(347, 0, 389, 363);
                frame.getContentPane().add(lblNewLabel_1);
        frame.setLocationRelativeTo(null);
        frame.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        frame.getRootPane().setDefaultButton(btnNewButton);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnNewButton.doClick();
                }
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        String username = textField.getText();
        String inputpassword = new String(password.getPassword());
        
        if (username.isEmpty() || inputpassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isValidLogin = checkLogin(username, inputpassword);

        if (isValidLogin) {
            
            frame.dispose();
            if (isAdmin) {
            	CTC window = new CTC();
            	 new connect();
                 try {
                	 window.sttc = new statistics();
                	 window.seller = new best_seller();
					 new Food_Cache();
					 new Drink_Cache();
	                 window.ep = new employee();
	                 window.frame.setVisible(true);
				} catch (SQLException e1) {
					
				}
                

            } else {
            	loggedInUserID = username;
                Staff_GUI st = new Staff_GUI();
                st.setVisible(true);
            	
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Tên đăng nhập hoặc mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static String getID() {
		return loggedInUserID;
    }
    public boolean checkLogin(String username, String password) {
        boolean isValidLogin = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
            String sql = "SELECT position FROM employment WHERE Emp_ID = ? AND password = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            String enPass= Crypt.encrypt(password);
            pstmt.setString(2, enPass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                isValidLogin = true;
                String position = rs.getString("position");
                
                if (position.equals("Admin")) {
                    this.isAdmin = true;
                } else {
                    this.isAdmin = false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isValidLogin;
    }

    public static void main(String []argStrings) {
    	new dangnhap1();
    }
}