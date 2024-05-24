package login;

import java.awt.EventQueue;
import javax.swing.*;

import admin.CTC;
import admin.employee;
import admin.statistics;

import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import admin.CTC;

//import admin.employee;
//import admin.statistics;
import connectDTB.connect;
import data_cache.Drink_Cache;
import data_cache.Food_Cache;
import staff.Staff_GUI;

public class dangnhap1 extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField password;
    private boolean isAdmin = true;
    public static String loggedInUserID = "101200";
    /**
     * Create the application.
     */

    public dangnhap1() {
       
        initialize();
        frame.setVisible(true);
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(dangnhap1.class.getResource("/image/logo.png")));
        lblNewLabel.setBounds(-50, -50, 450, 450);
        frame.getContentPane().add(lblNewLabel);


        JPanel panel = new JPanel();
        panel.setBounds(427, 134, 349, 113);
        frame.getContentPane().add(panel);
        panel.setLayout(new GridLayout(2, 2, 20, 20));

        JLabel lblNewLabel_3 = new JLabel("    USER NAME");
        lblNewLabel_3.setForeground(new Color(233, 150, 122));
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel.add(lblNewLabel_3);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(textField);
        textField.setColumns(15);

        JLabel lblNewLabel_2 = new JLabel("    PASSWORD");
        lblNewLabel_2.setForeground(new Color(233, 150, 122));
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel.add(lblNewLabel_2);

        password = new JPasswordField ();
        password.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(password);
        password.setColumns(15);

        JButton btnNewButton = new JButton("LOGIN");
        btnNewButton.addActionListener(this); 
        btnNewButton.setForeground(new Color(165, 42, 42));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.setBounds(507, 276, 206, 59);
        frame.getContentPane().add(btnNewButton);
        
        JLabel lblNewLabel_4 = new JLabel("LOGIN");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(460, 36, 278, 59);
        frame.getContentPane().add(lblNewLabel_4);
        frame.setLocationRelativeTo(null);
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
					 new Food_Cache();
					 new Drink_Cache();
	                 window.ep = new employee();
	                 window.frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
//            	adminInterface.setVisible(true);
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
            pstmt.setString(2, password);
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
