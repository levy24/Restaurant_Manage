package login;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import admin.CTC;
import admin.drinks;
import admin.employee;
import admin.food;
import connectDTB.connect;
import staff.Bill_Manage;
import staff.table_manage;

public class dangnhap1 extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField password;
    private boolean isAdmin;
    public String loggedInUserID;

    /**
     * Create the application.
     */
    public dangnhap1(boolean isAdmin) {
        this.isAdmin = isAdmin;
        initialize();
        frame.setVisible(true);
    }

    public dangnhap1() 
    {}

	/**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\THAO VY\\eclipse-workspace\\PBL\\src\\image\\logo.png"));
        lblNewLabel.setBounds(-50, -50, 450, 450);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1;
        if (isAdmin) {
            lblNewLabel_1 = new JLabel("LOGIN BY ADMIN");
        } else {
            lblNewLabel_1 = new JLabel("LOGIN BY EMPLOYEE");
        }
        lblNewLabel_1.setForeground(new Color(139, 0, 0));
        lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel_1.setBounds(507, 24, 206, 59);
        frame.getContentPane().add(lblNewLabel_1);

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
        	loggedInUserID = username;
            // Đóng cửa sổ hiện tại
            frame.dispose();
            if (isAdmin) {
            	CTC window = new CTC();
            	 new connect();
                 try {
					window.faFood = new food();
					 window.dr = new drinks();
	                 window.ep = new employee();
	                 window.frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    
            } else {
                table_manage vidu = new table_manage();
                vidu.show();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Tên đăng nhập hoặc mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getLoggedInUserID() {
        return loggedInUserID;
    }

    public boolean checkLogin(String username, String password) {
        boolean isValidLogin = false;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
            
            String sql;
            if (isAdmin) {
                sql = "SELECT * FROM employment WHERE Emp_ID = ? AND password = ? AND position = 'admin'";
            } else {
                sql = "SELECT * FROM employment WHERE Emp_ID = ? AND password = ? AND position = 'employee'";
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                isValidLogin = true;
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
    	
}
