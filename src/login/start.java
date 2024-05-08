package login;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class start extends JFrame implements ActionListener {
	
	private JPanel contentPane;
    private JButton adminButton;
    private JButton employeeButton;
    private JButton exit;
    private dangnhap1 dangNhapWindow; 
    

    public start() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		adminButton = new JButton("ADMIN");
		adminButton.setVerticalAlignment(SwingConstants.TOP);
		adminButton.setBackground(new Color(245, 245, 245));
		adminButton.setForeground(new Color(128, 0, 0));
		adminButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		adminButton.setBounds(35, 132, 133, 33);
		contentPane.add(adminButton);
		
		employeeButton = new JButton("Employee");
		employeeButton.setBackground(new Color(245, 255, 250));
		employeeButton.setForeground(new Color(128, 0, 0));
		employeeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		employeeButton.setBounds(217, 132, 146, 33);
		contentPane.add(employeeButton);
		
		exit = new JButton("Exit");
		exit.setBackground(new Color(245, 255, 250));
		exit.setForeground(new Color(128, 0, 0));
		exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exit.setBounds(103, 189, 161, 33);
		contentPane.add(exit);
		
		JLabel lblNewLabel = new JLabel("Select Your Position");
		lblNewLabel.setForeground(new Color(245, 255, 250));
		lblNewLabel.setFont(new Font("Forte", Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(48, 22, 286, 64);
		contentPane.add(lblNewLabel);
        
       
        adminButton.addActionListener(this);
        employeeButton.addActionListener(this);
        exit.addActionListener(this);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {          
            dangNhapWindow = new dangnhap1(true);
            dispose(); //
        } else if (e.getSource() == employeeButton) {
            dangNhapWindow = new dangnhap1(false); 
            dispose(); 
        }
        else if (e.getSource() == exit)
        	System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(start::new);
    }
}
